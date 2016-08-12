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
import dpiki.notificator.data.Requisition;

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
            for (Recommendation rec : recommendations) {
                ContentValues recValues = new ContentValues();
                recValues.put(DatabaseHelper.FIELD_RECOMMENDATIONS_ID_REQUIREMENT, rec.idRequirement);
                recValues.put(DatabaseHelper.FIELD_RECOMMENDATIONS_ID_REALTY, rec.idRealty);
                recValues.put(DatabaseHelper.FIELD_RECOMMENDATIONS_TYPE, rec.type);
                db.insert(DatabaseHelper.TABLE_RECOMMENDATIONS, null, recValues);
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
    public List<Long> readRecommendations(Long id, String type) {
        List<Long> retVal = new ArrayList<>();

        DatabaseHelper helper = new DatabaseHelper(mContext);
        SQLiteDatabase db = helper.getReadableDatabase();

        if (db == null)
            return retVal;

        try {
            String[] columns = { DatabaseHelper.FIELD_RECOMMENDATIONS_ID_REALTY };
            Cursor cursor = db.query(DatabaseHelper.TABLE_RECOMMENDATIONS, columns,
                    DatabaseHelper.FIELD_RECOMMENDATIONS_ID_REQUIREMENT + " = " + id + " AND "
                            + DatabaseHelper.FIELD_RECOMMENDATIONS_TYPE + " = '" + type + "'",
                    null, null, null, null);

            if (cursor == null)
                return retVal;

            try {
                while (cursor.moveToNext()) {
                    retVal.add(cursor.getLong(0));
                }
            } finally {
                cursor.close();
            }
        } finally {
            db.close();
        }

        return retVal;
    }

    /**
     * Removes all recommendations which does not relate to any requisition from r.
     * @param r list of requisitions.
     */
    public void clearRecommendations(List<Requisition> r) {
        String whereClause = "NOT (";
        for (Requisition i : r) {
            whereClause +=(
                    " OR (" +
                            DatabaseHelper.FIELD_RECOMMENDATIONS_TYPE + " = '" + i.type + " AND " +
                            DatabaseHelper.FIELD_RECOMMENDATIONS_ID + " = " + i.id +
                            ")"
            );
        }

        whereClause = whereClause.replaceFirst(" OR ", "");
        DatabaseHelper helper = new DatabaseHelper(mContext);
        SQLiteDatabase db = helper.getWritableDatabase();

        if (db == null)
            return;

        try {
            db.delete(DatabaseHelper.TABLE_RECOMMENDATIONS, whereClause, null);
        } finally {
            db.close();
        }
    }

    /**
     * Clears unreadRecommendationsCount of specified requirement.
     *
     * @param id  id of the requirement.
     * @param type  type of the requirement.
     */
    public void setUnreadRecommendationsCount(Long id, String type, int count) {
        DatabaseHelper helper = new DatabaseHelper(mContext);
        SQLiteDatabase db = helper.getWritableDatabase();
        String whereClause = DatabaseHelper.FIELD_REQUIREMENTS_ID + " = " + id + " AND " +
                    DatabaseHelper.FIELD_REQUIREMENTS_TYPE + " = " + type;

        mCacheRecommendations.put(type + ":" + id, count);

        if (db == null)
            return;

        try {
            Cursor c = db.query(DatabaseHelper.TABLE_REQUIREMENTS,
                    new String[] { DatabaseHelper.FIELD_REQUIREMENTS_UNREAD_RECOMMENDATIONS },
                    whereClause, null, null, null, null);

            assert c != null;

            try {
                if (c.moveToNext()) {
                    ContentValues cv = new ContentValues();
                    cv.put(DatabaseHelper.FIELD_REQUIREMENTS_UNREAD_RECOMMENDATIONS, count);
                    db.update(DatabaseHelper.TABLE_REQUIREMENTS, cv, whereClause, null);
                } else {
                    ContentValues cv = new ContentValues();
                    cv.put(DatabaseHelper.FIELD_REQUIREMENTS_UNREAD_RECOMMENDATIONS, count);
                    cv.put(DatabaseHelper.FIELD_REQUIREMENTS_ID, id);
                    cv.put(DatabaseHelper.FIELD_REQUIREMENTS_TYPE, type);
                    db.insert(DatabaseHelper.TABLE_REQUIREMENTS, null, cv);
                }
            } finally {
                c.close();
            }
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

        if (!mCacheRecommendations.containsKey(key)) {
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

                assert c != null;

                try {
                    if (c.moveToNext()) {
                        Integer unreadNotificationCount = c.getInt(0);
                        mCacheRecommendations.put(key, unreadNotificationCount);
                        return unreadNotificationCount;
                    } else {
                        ContentValues cv = new ContentValues();
                        cv.put(DatabaseHelper.FIELD_REQUIREMENTS_UNREAD_RECOMMENDATIONS, 0);
                        cv.put(DatabaseHelper.FIELD_REQUIREMENTS_ID, id);
                        cv.put(DatabaseHelper.FIELD_REQUIREMENTS_TYPE, type);
                        db.insert(DatabaseHelper.TABLE_REQUIREMENTS, null, cv);
                        mCacheRecommendations.put(key, 0);
                        return 0;
                    }
                } finally {
                    c.close();
                }
            } finally {
                db.close();
            }
        } else {
            return mCacheRecommendations.get(key);
        }
    }
}
