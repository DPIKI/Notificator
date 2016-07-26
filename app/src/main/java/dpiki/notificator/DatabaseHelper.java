package dpiki.notificator;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import dpiki.notificator.data.Client;
import dpiki.notificator.data.ClientLaptops;
import dpiki.notificator.data.ClientPhones;
import dpiki.notificator.data.Recommendation;

/**
 * Created by Lenovo on 05.07.2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MarketClients";
    public static final Integer DATABASE_VERSION = 1;

    public static final String TABLE_NOTIFICATION = "Notification";
    public static final String FIELD_ID_NOTIFICATION = "id_notification";
    public static final String FIELD_ID_CLIENT = "id_client";
    public static final String FIELD_ID_PHONE = "id_phone";

    public static final String TABLE_CLIENTS = "Clients";
    public static final String FIELD_ID = "id";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_PREF1 = "pref1";
    public static final String FIELD_PREF2 = "pref2";
    public static final String FIELD_PREF3 = "pref3";
    public static final String FIELD_UNREAD_NOTIFICATIONS = "unread_notifications";

    public static final String QUERY_CREATE_TABLE_CLIENTS =
            "CREATE TABLE " + TABLE_CLIENTS + " ("
                    + FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + FIELD_NAME + " TEXT NOT NULL, "
                    + FIELD_PREF1 + " TEXT, "
                    + FIELD_PREF2 + " TEXT, "
                    + FIELD_PREF3 + " TEXT, "
                    + FIELD_UNREAD_NOTIFICATIONS + " INTEGER);";

    public static final String QUERY_DROP_TABLE_CLIENTS =
            "DROP TABLE IF EXISTS " + TABLE_CLIENTS + ";";

    public static final String QUERY_CREATE_TABLE_NOTIFICATION =
            "CREATE TABLE " + TABLE_NOTIFICATION + " ("
                    + FIELD_ID_NOTIFICATION + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + FIELD_ID_CLIENT + " TEXT NOT NULL, "
                    + FIELD_ID_PHONE + " TEXT NOT NULL);";

    public static final String QUERY_DROP_TABLE_NOTIFICATION =
            "DROP TABLE IF EXIST " + TABLE_NOTIFICATION + ";";

    public static final String QUERY_COUNT_CLIENTS =
            "SELECT COUNT(" + FIELD_ID_CLIENT + ") FROM "
                    + TABLE_NOTIFICATION + " GROUP BY " + FIELD_ID_CLIENT;

    public static final String QUERY_COUNT_PHONES =
            "SELECT COUNT(" + FIELD_ID_PHONE + ") FROM "
                    + TABLE_NOTIFICATION + " GROUP BY " + FIELD_ID_PHONE;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static ArrayList<Client> readClients(Context context) {
        //TODO : implement
        return null;
    }

    public static void addNotifications(Context context, List<Recommendation> recommendations) {
    }

    public static void clearUnreadNotification(Context context, int clientId, String clientType) {
    }

    public static void addPhoneClients(Context context, List<ClientPhones> clients) {
    }

    public static void addLaptopClients(Context context, List<ClientLaptops> clients) {
    }

    public static void removePhoneClients(Context context, List<Integer> clientIds) {
    }

    public static void removeLaptopClients(Context context, List<Integer> clientIds) {
    }

    public static void fillTestData(Context context) {
        DatabaseHelper helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        try {
            db.execSQL(QUERY_DROP_TABLE_CLIENTS);
            db.execSQL(QUERY_CREATE_TABLE_CLIENTS);

            ContentValues values = new ContentValues();
            values.put(FIELD_NAME, "Vasya");
            values.put(FIELD_PREF1, "pref1");
            values.put(FIELD_PREF2, "pref2");
            values.put(FIELD_PREF3, "pref3");
            values.put(FIELD_UNREAD_NOTIFICATIONS, 1);
            db.insert(TABLE_CLIENTS, null, values);

            values = new ContentValues();
            values.put(FIELD_NAME, "Petro");
            values.put(FIELD_PREF1, "pref11");
            values.put(FIELD_PREF2, "pref22");
            values.put(FIELD_PREF3, "pref33");
            values.put(FIELD_UNREAD_NOTIFICATIONS, 1);
            db.insert(TABLE_CLIENTS, null, values);

            values = new ContentValues();
            values.put(FIELD_NAME, "Bes filtrov");
            values.put(FIELD_PREF1, "1");
            values.put(FIELD_PREF2, "2");
            values.put(FIELD_PREF3, "3");
            values.put(FIELD_UNREAD_NOTIFICATIONS, 2);
            db.insert(TABLE_CLIENTS, null, values);

            values = new ContentValues();
            values.put(FIELD_NAME, "s odnim filtrom");
            values.put(FIELD_PREF1, "dsz");
            values.put(FIELD_PREF2, "sldfj");
            values.put(FIELD_PREF3, "pref333");
            values.put(FIELD_UNREAD_NOTIFICATIONS, 3);
            db.insert(TABLE_CLIENTS, null, values);

            values = new ContentValues();
            values.put(FIELD_ID_CLIENT, 1);
            values.put(FIELD_ID_PHONE, 1);
            db.insert(TABLE_NOTIFICATION, null, values);

            values = new ContentValues();
            values.put(FIELD_ID_CLIENT, 1);
            values.put(FIELD_ID_PHONE, 2);
            db.insert(TABLE_NOTIFICATION, null, values);
            values = new ContentValues();
            values.put(FIELD_ID_CLIENT, 2);
            values.put(FIELD_ID_PHONE, 3);
            db.insert(TABLE_NOTIFICATION, null, values);
        } finally {
            db.close();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(QUERY_CREATE_TABLE_CLIENTS);
        db.execSQL(QUERY_CREATE_TABLE_NOTIFICATION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(QUERY_DROP_TABLE_CLIENTS);
        db.execSQL(QUERY_DROP_TABLE_NOTIFICATION);
        onCreate(db);
    }
}
