package dpiki.notificator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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
    private Context mContext;
    /**
     * Key - type:id
     */
    private Map<String, Integer> mCacheRecommendations;
    public static final String TAG = "DatabaseUtils";

    public DatabaseUtils(Context context) {
        this.mContext = context;
        this.mCacheRecommendations = new TreeMap<>();
    }

    /**
     * Returns all Requirement.
     *
     * @return List<Requirement>
     */
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
            Cursor cursor = db.query(DatabaseHelper.TABLE_REQUIREMENTS,
                    columns, null, null, null, null,
                    DatabaseHelper.FIELD_REQUIREMENTS_UNREAD_RECOMMENDATIONS);

            if (cursor == null)
                return requirements;

            try {
                while (cursor.moveToNext()) {
                    Requirement requirement = new Requirement();
                    requirement.id = cursor.getLong(0);
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
     */
    public void updateRequirements(List<Requirement> requirements) {
        DatabaseHelper helper = new DatabaseHelper(mContext);
        SQLiteDatabase db = helper.getWritableDatabase();

        if (db == null)
            return;

        try {
            db.delete(DatabaseHelper.TABLE_REQUIREMENTS, null, null);
            mCacheRecommendations = new TreeMap<>();

            String condition = "NOT(";
            if (!requirements.isEmpty()) {
                for (Requirement requirement : requirements) {
                    ContentValues values = new ContentValues();
                    values.put(DatabaseHelper.FIELD_REQUIREMENTS_ID, requirement.id);
                    values.put(DatabaseHelper.FIELD_REQUIREMENTS_TYPE, requirement.type);
                    values.put(DatabaseHelper.FIELD_REQUIREMENTS_UNREAD_RECOMMENDATIONS,
                            requirement.unreadRecommendations);
                    db.insertWithOnConflict(DatabaseHelper.TABLE_REQUIREMENTS, "", values,
                            SQLiteDatabase.CONFLICT_IGNORE);
                    condition += " OR ("
                            + DatabaseHelper.FIELD_RECOMMENDATIONS_ID_REQUIREMENT + " = " + requirement.id + " AND "
                            + DatabaseHelper.FIELD_RECOMMENDATIONS_TYPE + " = " + requirement.type + ")";

                    mCacheRecommendations.put(requirement.type + ":" + requirement.id,
                            requirement.unreadRecommendations);
                }
                Log.d(TAG, "DELETE FROM " + DatabaseHelper.TABLE_RECOMMENDATIONS
                        + "WHERE" + condition);
            }

            // TODO: need test
            db.delete(DatabaseHelper.TABLE_RECOMMENDATIONS, condition.replaceFirst(" OR ", ""), null);
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
                recValues.put(DatabaseHelper.FIELD_RECOMMENDATIONS_ID_REQUIREMENT, rec.idRequirement);
                recValues.put(DatabaseHelper.FIELD_RECOMMENDATIONS_ID_REALTY, rec.idRealty);
                recValues.put(DatabaseHelper.FIELD_RECOMMENDATIONS_TYPE, rec.type);
                db.insert(DatabaseHelper.TABLE_RECOMMENDATIONS, null, recValues);

                String requirementString = rec.type + ":" + rec.idRequirement;
                if (!newRecommendationsCount.containsKey(requirementString)) {
                    newRecommendationsCount.put(requirementString,
                            getUnreadRecommendationsCount(rec.idRequirement, rec.type));
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
                                DatabaseHelper.FIELD_REQUIREMENTS_TYPE + " = '" + type + "'", null);
                mCacheRecommendations.put(type + ":" + id, i.getValue());
            }
        } finally {
            db.close();
        }
    }

    /**
     * Returns id recommendations of specified typeRequirement and idRequirement.
     *
     * @param id - id of the requirement.
     * @param type - type of the requirement.
     * @return List<Integer>
     */
    public List<Integer> readRecommendation(Long id, String type) {
        List<Integer> idRecommendations = new ArrayList<>();

        DatabaseHelper helper = new DatabaseHelper(mContext);
        SQLiteDatabase db = helper.getReadableDatabase();

        if (db == null)
            return idRecommendations;

        try {
            String[] columns = { DatabaseHelper.FIELD_RECOMMENDATIONS_ID_REALTY };
            Cursor cursor = db.query(DatabaseHelper.TABLE_RECOMMENDATIONS, columns,
                    DatabaseHelper.FIELD_RECOMMENDATIONS_ID_REQUIREMENT + " = " + id + " AND "
                            + DatabaseHelper.FIELD_RECOMMENDATIONS_TYPE + " = '" + type + "'",
                    null, null, null, null);

            if (cursor == null)
                return idRecommendations;

            try {
                while (cursor.moveToNext()) {
                    idRecommendations.add(cursor.getInt(0));
                }
            } finally {
                cursor.close();
            }
        } finally {
            db.close();
        }

        return idRecommendations;
    }

    /**
     * Clears unreadRecommendationsCount of specified requirement.
     *
     * @param id - id of the requirement.
     * @param type - type of the requirement.
     */
    public void clearUnreadRecommendationsCount(int id, String type) {
        DatabaseHelper helper = new DatabaseHelper(mContext);
        SQLiteDatabase db = helper.getWritableDatabase();
        mCacheRecommendations.clear();

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
     * @return int
     * @param id - id of the requirement.
     * @param type - type of the requirement.
     */
    public int getUnreadRecommendationsCount(Long id, String type) {
        String key = type + ":" + id;
        if (mCacheRecommendations.containsKey(key)) {
            return mCacheRecommendations.get(key);
        } else {
            DatabaseHelper helper = new DatabaseHelper(mContext);
            SQLiteDatabase db = helper.getWritableDatabase();

            if (db == null)
                return 0;

            try {
                Cursor c = db.query(DatabaseHelper.TABLE_REQUIREMENTS,
                        new String[]{DatabaseHelper.FIELD_REQUIREMENTS_UNREAD_RECOMMENDATIONS},
                        DatabaseHelper.FIELD_REQUIREMENTS_ID + " = " + id + " AND " +
                                DatabaseHelper.FIELD_REQUIREMENTS_TYPE + " = '" + type + "'",
                        null, null, null, null);

                if (c == null)
                    return 0;

                try {
                    if (c.moveToNext()) {
                        Integer unreadNotificationCount = c.getInt(0);
                        mCacheRecommendations.put(type + ":" + id, unreadNotificationCount);
                        return unreadNotificationCount;
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

}
