package dpiki.notificator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import java.util.ArrayList;

import dpiki.notificator.data.MarketClient;

/**
 * Created by Lenovo on 05.07.2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MarketClients";
    public static final Integer DATABASE_VERSION = 1;

    public static final String TABLE_CLIENTS = "Clients";
    public static final String FIELD_ID = "id";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_PREF1 = "pref1";
    public static final String FIELD_PREF2 = "pref2";
    public static final String FIELD_PREF3 = "pref3";

    public static final String QUERY_CREATE_TABLE_CLIENTS = "CREATE TABLE " + TABLE_CLIENTS + " ("
            + FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + FIELD_NAME + " TEXT NOT NULL, "
            + FIELD_PREF1 + " TEXT, "
            + FIELD_PREF2 + " TEXT, "
            + FIELD_PREF3 + " TEXT);";
    public static final String QUERY_DROP_TABLE_CLIENTS = "DROP TABLE IF EXIST " + TABLE_CLIENTS + ";";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(QUERY_CREATE_TABLE_CLIENTS);
        fillTestData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(QUERY_DROP_TABLE_CLIENTS);
        onCreate(db);
    }

    public static ArrayList<MarketClient> readClients(Context context) {
        ArrayList<MarketClient> ret_val = new ArrayList<>();

        DatabaseHelper helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        if (db == null)
            return ret_val;

        try {
            String[] columns = {FIELD_ID, FIELD_NAME, FIELD_PREF1, FIELD_PREF2, FIELD_PREF3};
            Cursor cursor = db.query(TABLE_CLIENTS, columns, null, null, null, null, null);

            if (cursor == null)
                return ret_val;

            try {
                while (cursor.moveToNext()) {
                    MarketClient newClient = new MarketClient(
                            cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4)
                    );
                    ret_val.add(newClient);
                }
            } finally {
                cursor.close();
            }
        } finally {
            db.close();
        }

        return ret_val;
    }

    void fillTestData(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(FIELD_NAME, "Vasya");
        values.put(FIELD_PREF1, "pref1");
        values.put(FIELD_PREF2, "pref2");
        values.put(FIELD_PREF3, "pref3");
        db.insert(TABLE_CLIENTS, null, values);

        values = new ContentValues();
        values.put(FIELD_NAME, "Petro");
        values.put(FIELD_PREF1, "pref11");
        values.put(FIELD_PREF2, "pref22");
        values.put(FIELD_PREF3, "pref33");
        db.insert(TABLE_CLIENTS, null, values);

        values = new ContentValues();
        values.put(FIELD_NAME, "Bes filtrov");
        values.put(FIELD_PREF1, "");
        values.put(FIELD_PREF2, "");
        values.put(FIELD_PREF3, "");
        db.insert(TABLE_CLIENTS, null, values);

        values = new ContentValues();
        values.put(FIELD_NAME, "s odnim filtrom");
        values.put(FIELD_PREF1, "");
        values.put(FIELD_PREF2, "");
        values.put(FIELD_PREF3, "pref333");
        db.insert(TABLE_CLIENTS, null, values);

    }
}
