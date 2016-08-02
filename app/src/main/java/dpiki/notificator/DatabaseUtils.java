package dpiki.notificator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.inject.Inject;

import dpiki.notificator.data.Recommendation;
import dpiki.notificator.data.Requirement;
import dpiki.notificator.network.DataFetcher;

/**
 * Created by Lenovo on 02.08.2016.
 */
public class DatabaseUtils {
    @Inject
    Context mContext;

    public DatabaseUtils() {
        App.getInstance().inject(this);
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
                    requirement.type = cursor.getString(2);
                    requirement.unreadRecommendations = cursor.getInt(3);
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

    public void updateRequirements(List<Requirement> requirements) {
        DatabaseHelper helper = new DatabaseHelper(mContext);
        SQLiteDatabase db = helper.getWritableDatabase();

        if (db == null)
            return;

        try {
            db.delete(DatabaseHelper.TABLE_REQUIREMENTS, null, null);

            String query = "DELETE FROM " + DatabaseHelper.TABLE_RECOMMENDATIONS + " WHERE ";
            for (Requirement requirement : requirements) {
                ContentValues values = new ContentValues();
                values.put(DatabaseHelper.FIELD_REQUIREMENTS_ID, requirement.id);
                values.put(DatabaseHelper.FIELD_REQUIREMENTS_TYPE, requirement.type);
                values.put(DatabaseHelper.FIELD_REQUIREMENTS_UNREAD_RECOMMENDATIONS, requirement.unreadRecommendations);
                db.insertWithOnConflict(DatabaseHelper.TABLE_REQUIREMENTS, "", values, SQLiteDatabase.CONFLICT_IGNORE);
                query += "AND "
                        + DatabaseHelper.FIELD_RECOMMENDATIONS_ID_REQUIREMENT + " <> " + requirement.id + " AND "
                        + DatabaseHelper.FIELD_RECOMMENDATIONS_TYPE + " <> " + "'" + requirement.type + "'";
            }

            // TODO: need test
            db.execSQL(query.replaceFirst("AND ", ""));
        } finally {
            db.close();
        }
    }

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

    public void clearUnreadRecommendationsCount(int clientId, String clientType) {
        DatabaseHelper helper = new DatabaseHelper(mContext);
        SQLiteDatabase db = helper.getWritableDatabase();

        if (db == null)
            return;

        try {
            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.FIELD_REQUIREMENTS_UNREAD_RECOMMENDATIONS, 0);
            db.update(
                    DatabaseHelper.TABLE_REQUIREMENTS,
                    values, DatabaseHelper.FIELD_REQUIREMENTS_ID + " = " + clientId + " AND " +
                            DatabaseHelper.FIELD_REQUIREMENTS_TYPE + " = '" + clientType + "'",
                    null);
        } finally {
            db.close();
        }
    }

    public int getUnreadNotificationsCount;
}
