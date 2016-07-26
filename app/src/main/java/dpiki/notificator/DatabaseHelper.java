package dpiki.notificator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import dpiki.notificator.data.Client;
import dpiki.notificator.data.LaptopClient;
import dpiki.notificator.data.LaptopRecommendation;
import dpiki.notificator.data.PhoneClient;
import dpiki.notificator.data.PhoneRecommendation;
import dpiki.notificator.data.Recommendation;

/**
 * Created by Lenovo on 05.07.2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MarketClients";
    public static final Integer DATABASE_VERSION = 1;

    public static final String FIELD_ID = "id";
    public static final String FIELD_FIO = "fio";
    public static final String FIELD_UNREAD_NOTIFICATIONS = "unread_notifications";

    public static final String TABLE_PHONE_CLIENTS = "PhoneClients";
    public static final String FIELD_PREF1 = "pref1";
    public static final String FIELD_PREF2 = "pref2";
    public static final String FIELD_PREF3 = "pref3";
    public static final String QUERY_CREATE_TABLE_PHONE_CLIENTS =
            "CREATE TABLE " + TABLE_PHONE_CLIENTS + " ("
                    + FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + FIELD_FIO + " TEXT NOT NULL, "
                    + FIELD_PREF1 + " TEXT, "
                    + FIELD_PREF2 + " TEXT, "
                    + FIELD_PREF3 + " TEXT, "
                    + FIELD_UNREAD_NOTIFICATIONS + " INTEGER);";
    public static final String QUERY_DROP_TABLE_PHONE_CLIENTS =
            "DROP TABLE IF EXISTS " + TABLE_PHONE_CLIENTS + ";";
    public static final String QUERY_SELECT_PHONE_CLIENT_BY_ID =
            "SELECT * FROM " + TABLE_PHONE_CLIENTS + " WHERE "
                    + FIELD_ID  + " = ";

    public static final String TABLE_LAPTOP_CLIENTS = "PhoneClients";
    public static final String FIELD_PREF11 = "pref11";
    public static final String FIELD_PREF12 = "pref12";
    public static final String FIELD_PREF13 = "pref13";
    public static final String FIELD_PREF14 = "pref14";
    public static final String QUERY_CREATE_TABLE_LAPTOP_CLIENTS =
            "CREATE TABLE " + TABLE_LAPTOP_CLIENTS + " ("
                    + FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + FIELD_FIO + " TEXT NOT NULL, "
                    + FIELD_PREF11 + " TEXT, "
                    + FIELD_PREF12 + " TEXT, "
                    + FIELD_PREF13 + " TEXT, "
                    + FIELD_PREF14 + " TEXT, "
                    + FIELD_UNREAD_NOTIFICATIONS + " INTEGER);";
    public static final String QUERY_DROP_TABLE_LAPTOP_CLIENTS =
            "DROP TABLE IF EXISTS " + TABLE_LAPTOP_CLIENTS + ";";
    public static final String QUERY_SELECT_LAPTOP_CLIENT_BY_ID =
            "SELECT * FROM " + TABLE_LAPTOP_CLIENTS + " WHERE "
                    + FIELD_ID  + " = ";

    public static final String TABLE_NOTIFICATION = "Notification";
    public static final String FIELD_ID_NOTIFICATION = "id_notification";
    public static final String FIELD_ID_CLIENT = "id_client";
    public static final String FIELD_ID_PRODUCT = "id_product";
    public static final String FIELD_TYPE_CLIENT = "type_client";
    public static final String QUERY_CREATE_TABLE_NOTIFICATION =
            "CREATE TABLE " + TABLE_NOTIFICATION + " ("
                    + FIELD_ID_NOTIFICATION + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + FIELD_ID_CLIENT + " INTEGER, "
                    + FIELD_ID_PRODUCT + " INTEGER, "
                    + FIELD_TYPE_CLIENT + " TEXT);";
    public static final String QUERY_DROP_TABLE_NOTIFICATION =
            "DROP TABLE IF EXIST " + TABLE_NOTIFICATION + ";";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static ArrayList<Client> readClients(Context context) {
        ArrayList<Client> clients = new ArrayList<>();
        clients.addAll(readPhoneClients(context));
        clients.addAll(readLaptopClients(context));

        return clients;
    }

    public static ArrayList<PhoneClient> readPhoneClients(Context context) {
        ArrayList<PhoneClient> phoneClients = new ArrayList<>();

        DatabaseHelper helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        if (db == null)
            return phoneClients;

        try {
            String[] columns = {
                    FIELD_ID,
                    FIELD_FIO,
                    FIELD_PREF1,
                    FIELD_PREF2,
                    FIELD_PREF3,
                    FIELD_UNREAD_NOTIFICATIONS
            };
            Cursor cursor = db.query(TABLE_PHONE_CLIENTS, columns, null, null, null, null,
                    FIELD_UNREAD_NOTIFICATIONS);

            if (cursor == null)
                return phoneClients;

            try {
                while (cursor.moveToNext()) {
                    PhoneClient phoneClient = new PhoneClient();
                    phoneClient.id = cursor.getInt(0);
                    phoneClient.fio = cursor.getString(1);
                    phoneClient.pref1 = cursor.getString(2);
                    phoneClient.pref2 = cursor.getString(3);
                    phoneClient.pref3 = cursor.getString(4);
                    phoneClient.notifCount = cursor.getInt(5);

                    phoneClients.add(phoneClient);
                }
            } finally {
                cursor.close();
            }
        } finally {
            db.close();
        }

        return phoneClients;
    }

    public static ArrayList<LaptopClient> readLaptopClients(Context context) {
        ArrayList<LaptopClient> laptopClients = new ArrayList<>();

        DatabaseHelper helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        if (db == null)
            return laptopClients;

        try {
            String[] columns = {
                    FIELD_ID,
                    FIELD_FIO,
                    FIELD_PREF11,
                    FIELD_PREF12,
                    FIELD_PREF13,
                    FIELD_PREF14,
                    FIELD_UNREAD_NOTIFICATIONS };
            Cursor cursor = db.query(TABLE_LAPTOP_CLIENTS, columns, null, null, null, null,
                    FIELD_UNREAD_NOTIFICATIONS);

            if (cursor == null)
                return laptopClients;

            try {
                while (cursor.moveToNext()) {
                    LaptopClient laptopClient = new LaptopClient();
                    laptopClient.id = cursor.getInt(0);
                    laptopClient.fio = cursor.getString(1);
                    laptopClient.pref11 = cursor.getString(2);
                    laptopClient.pref12 = cursor.getString(3);
                    laptopClient.pref13 = cursor.getString(4);
                    laptopClient.pref14 = cursor.getString(5);
                    laptopClient.notifCount = cursor.getInt(6);

                    laptopClients.add(laptopClient);
                }
            } finally {
                cursor.close();
            }
        } finally {
            db.close();
        }

        return laptopClients;
    }

    public static void addNotifications(Context context, List<Recommendation> recommendations) {
        DatabaseHelper helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        if (db == null)
            return;
        try {
            Iterator<Recommendation> i = recommendations.iterator();
            while (i.hasNext()){
                Recommendation recommendation = i.next();

                ContentValues notifValues = new ContentValues();
                notifValues.put(FIELD_ID_CLIENT, recommendation.client.id);
                notifValues.put(FIELD_TYPE_CLIENT, recommendation.client.type);

                ContentValues clientValues = new ContentValues();
                clientValues.put(FIELD_ID_CLIENT, recommendation.client.id);
                clientValues.put(FIELD_FIO, recommendation.client.fio);
                clientValues.put(FIELD_TYPE_CLIENT, recommendation.client.type);

                if (recommendation.client.type.equals("laptop")) {
                    notifValues.put(FIELD_ID_PRODUCT,
                            ((LaptopRecommendation) recommendation).laptop.id);

                    Cursor cursor = db.rawQuery
                            (QUERY_SELECT_PHONE_CLIENT_BY_ID + recommendation.client.id, null);
                    int oldNotifCount =
                            cursor.getInt(cursor.getColumnIndex(FIELD_UNREAD_NOTIFICATIONS));
                    cursor.close();

                    clientValues.put(FIELD_UNREAD_NOTIFICATIONS,
                            oldNotifCount + recommendation.client.notifCount);

                    db.update(TABLE_LAPTOP_CLIENTS, clientValues, FIELD_ID + " = ?",
                            new String[]{String.
                                    valueOf(((LaptopRecommendation) recommendation).laptop.id)});
                }else if (recommendation.client.type.equals("phone")){
                    notifValues.put(FIELD_ID_PRODUCT,
                            ((PhoneRecommendation) recommendation).phone.id);

                    //Получаем запись клиента
                    Cursor cursor = db.rawQuery
                            (QUERY_SELECT_LAPTOP_CLIENT_BY_ID + recommendation.client.id, null);
                    //Получаем количество непрочитанных нотификаций
                    int oldNotifCount =
                            cursor.getInt(cursor.getColumnIndex(FIELD_UNREAD_NOTIFICATIONS));
                    cursor.close();

                    clientValues.put(FIELD_UNREAD_NOTIFICATIONS,
                            oldNotifCount + recommendation.client.notifCount);

                    //Обновляем запись в базе клиентов(старые нотификации + новые)
                    db.update(TABLE_PHONE_CLIENTS, clientValues, FIELD_ID + " = ?",
                            new String[]{String.
                                    valueOf(((PhoneRecommendation) recommendation).phone.id)});
                }
                db.insert(TABLE_NOTIFICATION, null, notifValues);
            }
        }finally {
            db.close();
        }
    }

    public static void clearUnreadNotification(Context context, int clientId, String clientType) {
        DatabaseHelper helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        if (db == null)
            return;

        try {
            ContentValues values = new ContentValues();
            values.put(FIELD_UNREAD_NOTIFICATIONS, 0);
            if (clientType.equals("phone")) {
                db.update(TABLE_PHONE_CLIENTS, values, FIELD_ID + " = " + clientId, null);
            }else if (clientType.equals("laptop")){
                db.update(TABLE_LAPTOP_CLIENTS, values, FIELD_ID + " = " + clientId, null);
            }
        } finally {
            db.close();
        }
    }

    public static void updatePhoneClients(Context context, List<PhoneClient> clients) {
        DatabaseHelper helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        if (db == null)
            return;

        try {
            db.execSQL(QUERY_DROP_TABLE_PHONE_CLIENTS);

            Iterator<PhoneClient> i = clients.iterator();
            while (i.hasNext()) {
                PhoneClient phoneClient = i.next();
                ContentValues values = new ContentValues();
                values.put(FIELD_ID, phoneClient.id);
                values.put(FIELD_FIO, phoneClient.fio);
                values.put(FIELD_PREF1, phoneClient.pref1);
                values.put(FIELD_PREF2, phoneClient.pref2);
                values.put(FIELD_PREF3, phoneClient.pref3);
                values.put(FIELD_TYPE_CLIENT, phoneClient.type);
                values.put(FIELD_UNREAD_NOTIFICATIONS, phoneClient.notifCount);
                db.insertWithOnConflict(TABLE_PHONE_CLIENTS, "", values, SQLiteDatabase.CONFLICT_IGNORE);
            }
        } finally {
            db.close();
        }
    }

    public static void updateLaptopClients(Context context, List<LaptopClient> clients) {
        DatabaseHelper helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        if (db == null)
            return;

        try {
            db.execSQL(QUERY_DROP_TABLE_LAPTOP_CLIENTS);

            Iterator<LaptopClient> i = clients.iterator();
            while (i.hasNext()) {
                LaptopClient laptopClient = i.next();
                ContentValues values = new ContentValues();
                values.put(FIELD_ID, laptopClient.id);
                values.put(FIELD_FIO, laptopClient.fio);
                values.put(FIELD_PREF11, laptopClient.pref11);
                values.put(FIELD_PREF12, laptopClient.pref12);
                values.put(FIELD_PREF13, laptopClient.pref13);
                values.put(FIELD_PREF14, laptopClient.pref14);
                values.put(FIELD_TYPE_CLIENT, laptopClient.type);
                values.put(FIELD_UNREAD_NOTIFICATIONS, laptopClient.notifCount);
                db.insertWithOnConflict(TABLE_LAPTOP_CLIENTS, "", values, SQLiteDatabase.CONFLICT_IGNORE);
            }
        } finally {
            db.close();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(QUERY_CREATE_TABLE_PHONE_CLIENTS);
        db.execSQL(QUERY_CREATE_TABLE_LAPTOP_CLIENTS);
        db.execSQL(QUERY_CREATE_TABLE_NOTIFICATION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(QUERY_DROP_TABLE_PHONE_CLIENTS);
        db.execSQL(QUERY_DROP_TABLE_LAPTOP_CLIENTS);
        db.execSQL(QUERY_DROP_TABLE_NOTIFICATION);
        onCreate(db);
    }
}
