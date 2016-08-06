package dpiki.notificator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import dpiki.notificator.data.Recommendation;
import dpiki.notificator.network.SyncMarketService;

/**
 * Created by Lenovo on 05.07.2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Notificator";
    public static final Integer DATABASE_VERSION = 1;

    public static final String TABLE_REQUIREMENTS = "Requirements";
    public static final String FIELD_REQUIREMENTS_ID = "id_requirement";
    public static final String FIELD_REQUIREMENTS_FIO = "fio";
    public static final String FIELD_REQUIREMENTS_TYPE = "type";
    public static final String FIELD_REQUIREMENTS_UNREAD_RECOMMENDATIONS = "unread_recommendations";
    public static final String QUERY_CREATE_TABLE_REQUIREMENTS =
            "CREATE TABLE " + TABLE_REQUIREMENTS + " ("
                    + FIELD_REQUIREMENTS_ID + " INTEGER, "
                    + FIELD_REQUIREMENTS_FIO + " TEXT, "
                    + FIELD_REQUIREMENTS_TYPE + " TEXT, "
                    + FIELD_REQUIREMENTS_UNREAD_RECOMMENDATIONS + " INTEGER);";
    public static final String QUERY_DROP_TABLE_REQUIREMENTS =
            "DROP TABLE IF EXISTS " + TABLE_REQUIREMENTS + ";";

    public static final String TABLE_RECOMMENDATIONS = "Recommendations";
    public static final String FIELD_RECOMMENDATIONS_ID = "id_recommendation";
    public static final String FIELD_RECOMMENDATIONS_ID_REALTY = "id_product";
    public static final String FIELD_RECOMMENDATIONS_ID_REQUIREMENT = "id_requirement";
    public static final String FIELD_RECOMMENDATIONS_TYPE = "type";
    public static final String QUERY_CREATE_TABLE_RECOMMENDATIONS =
            "CREATE TABLE " + TABLE_RECOMMENDATIONS + " ("
                    + FIELD_RECOMMENDATIONS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + FIELD_RECOMMENDATIONS_ID_REALTY + " INTEGER, "
                    + FIELD_RECOMMENDATIONS_ID_REQUIREMENT + " INTEGER, "
                    + FIELD_RECOMMENDATIONS_TYPE + " TEXT);";
    public static final String QUERY_DROP_TABLE_RECOMMENDATIONS =
            "DROP TABLE IF EXIST " + TABLE_RECOMMENDATIONS + ";";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(QUERY_CREATE_TABLE_REQUIREMENTS);
        db.execSQL(QUERY_CREATE_TABLE_RECOMMENDATIONS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(QUERY_DROP_TABLE_REQUIREMENTS);
        db.execSQL(QUERY_DROP_TABLE_RECOMMENDATIONS);
        onCreate(db);
    }
}
