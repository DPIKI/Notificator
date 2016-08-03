package dpiki.notificator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import dpiki.notificator.data.Recommendation;
import dpiki.notificator.data.Requirement;

/**
 * Created by Lenovo on 02.08.2016.
 */
public class DatabaseUtils {
    Context mContext;

    public DatabaseUtils(Context context) {
        this.mContext = context;
    }

    public List<Requirement> readRequirements() {
        List<Requirement> requirements = new ArrayList<>();

        DatabaseHelper helper = new DatabaseHelper(mContext);
        SQLiteDatabase db = helper.getReadableDatabase();

        if (db == null)
            return requirements;

        try {
            String[] columns = {
                    DatabaseHelper.FIELD_REQUIREMENTS_ID,
                    DatabaseHelper.FIELD_REQUIREMENTS_TYPE,
                    DatabaseHelper.FIELD_REQUIREMENTS_UNREAD_RECOMMENDATIONS
            };
            Cursor cursor = db.query(DatabaseHelper.TABLE_REQUIREMENTS, columns, null, null, null, null,
                    DatabaseHelper.FIELD_REQUIREMENTS_UNREAD_RECOMMENDATIONS);

            if (cursor == null)
                return requirements;

            try {
                while (cursor.moveToNext()) {
                    Requirement requirement = new Requirement();
                    requirement.id = cursor.getInt(0);
                    requirement.type = cursor.getString(1);
                    requirement.unreadRecommendations = cursor.getInt(2);
                    requirements.add(requirement);
                }
            } finally {
                cursor.close();
            }
        } finally {
            db.close();
        }

        return requirements;
    }

    /**
     * Method removes requirements with the specified type,
     * adds new requirements and removes all recommendations
     * to removed requirements.
     *
     * @param requirements - all requirements are same type.
     * @param type         - type of the requirements.
     */
    public void updateRequirements(List<Requirement> requirements, String type) {
        DatabaseHelper helper = new DatabaseHelper(mContext);
        SQLiteDatabase db = helper.getWritableDatabase();

        if (db == null)
            return;

        try {
            // TODO : where type = typereq
            db.delete(DatabaseHelper.TABLE_REQUIREMENTS, null, null);

            String query = DatabaseHelper.FIELD_RECOMMENDATIONS_TYPE + " = " + "'" + type + "' ";
            if (!requirements.isEmpty()) {
                for (Requirement requirement : requirements) {
                    ContentValues values = new ContentValues();
                    values.put(DatabaseHelper.FIELD_REQUIREMENTS_ID, requirement.id);
                    values.put(DatabaseHelper.FIELD_REQUIREMENTS_TYPE, requirement.type);
                    values.put(DatabaseHelper.FIELD_REQUIREMENTS_UNREAD_RECOMMENDATIONS,
                            requirement.unreadRecommendations);
                    db.insertWithOnConflict(DatabaseHelper.TABLE_REQUIREMENTS, "", values,
                            SQLiteDatabase.CONFLICT_IGNORE);
                    query += "AND " + DatabaseHelper.FIELD_RECOMMENDATIONS_ID_REQUIREMENT
                            + " <> " + requirement.id;
                }
                query.replaceFirst("AND ", "");
            }

            // TODO: need test
            db.delete(DatabaseHelper.TABLE_RECOMMENDATIONS, query, null);
        } finally {
            db.close();
        }
    }

    /**
     * Method adds new recommendations and updates unreadRecommendationsCount
     * field in Requirements table.
     *
     * @param recommendations - list of new recommendations.
     */
    public void addRecommendations(List<Recommendation> recommendations) {
        DatabaseHelper helper = new DatabaseHelper(mContext);
        SQLiteDatabase db = helper.getWritableDatabase();

        if (db == null)
            return;

        try {
            Map<String, Integer> newRecommendationsCount = new TreeMap<>();
            for (Recommendation rec : recommendations) {
                ContentValues recValues = new ContentValues();
                recValues.put(DatabaseHelper.FIELD_RECOMMENDATIONS_ID_REQUIREMENT, rec.requirement.id);
                recValues.put(DatabaseHelper.FIELD_RECOMMENDATIONS_ID_REALTY, rec.realty.id);
                recValues.put(DatabaseHelper.FIELD_RECOMMENDATIONS_TYPE, rec.requirement.type);
                db.insert(DatabaseHelper.TABLE_RECOMMENDATIONS, null, recValues);

                String requirementString = rec.requirement.type + ":" + rec.requirement.id;
                if (!newRecommendationsCount.containsKey(requirementString)) {
                    newRecommendationsCount.put(requirementString, rec.requirement.unreadRecommendations);
                }
                newRecommendationsCount.put(requirementString,
                        newRecommendationsCount.get(requirementString) + 1);
            }

            for (Map.Entry<String, Integer> i : newRecommendationsCount.entrySet()) {
                String[] pair = i.getKey().split(":");
                int id = Integer.parseInt(pair[1]);
                String type = pair[0];
                ContentValues clientValues = new ContentValues();
                clientValues.put(DatabaseHelper.FIELD_REQUIREMENTS_UNREAD_RECOMMENDATIONS, i.getValue());
                db.update(DatabaseHelper.TABLE_REQUIREMENTS, clientValues,
                        DatabaseHelper.FIELD_REQUIREMENTS_ID + " = " + id + " AND " +
                                DatabaseHelper.FIELD_REQUIREMENTS_TYPE + " = '" + type + "'",
                        null);
            }
        } finally {
            db.close();
        }
    }

    /**
     * Clears unreadRecommendationsCount of specified requirement.
     *
     * @param id
     * @param type
     */
    public void clearUnreadRecommendationsCount(int id, String type) {
        DatabaseHelper helper = new DatabaseHelper(mContext);
        SQLiteDatabase db = helper.getWritableDatabase();

        if (db == null)
            return;

        try {
            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.FIELD_REQUIREMENTS_UNREAD_RECOMMENDATIONS, 0);
            db.update(
                    DatabaseHelper.TABLE_REQUIREMENTS,
                    values, DatabaseHelper.FIELD_REQUIREMENTS_ID + " = " + id + " AND " +
                            DatabaseHelper.FIELD_REQUIREMENTS_TYPE + " = '" + type + "'",
                    null);
        } finally {
            db.close();
        }
    }

    /**
     * Returns unreadRecommendationsCount of specified requirement.
     *
     * @param id
     * @param type
     */
    public int getUnreadRecommendationsCount(Integer id, String type) {
        DatabaseHelper helper = new DatabaseHelper(mContext);
        SQLiteDatabase db = helper.getWritableDatabase();

        if (db == null)
            return 0;

        try {
            Cursor c = db.query(DatabaseHelper.TABLE_REQUIREMENTS,
                    new String[] { DatabaseHelper.FIELD_REQUIREMENTS_UNREAD_RECOMMENDATIONS },
                    DatabaseHelper.FIELD_REQUIREMENTS_ID + " = " + id + " AND " +
                    DatabaseHelper.FIELD_REQUIREMENTS_TYPE + " = '" + type + "'",
                    null, null, null, null);

            if (c == null)
                return 0;

            try {
                if (c.moveToNext()) {
                    return c.getInt(0);
                } else {
                    return 0;
                }
            } finally {
                c.close();
            }
        } finally {
            db.close();
        }
    }
}
