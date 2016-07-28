package dpiki.notificator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import dpiki.notificator.data.Client;
import dpiki.notificator.data.laptop.Laptop;
import dpiki.notificator.data.laptop.LaptopClient;
import dpiki.notificator.data.phone.PhoneClient;
import dpiki.notificator.data.Recommendation;

/**
 * Created by Lenovo on 05.07.2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Notificator";
    public static final Integer DATABASE_VERSION = 1;

    public static final String TABLE_CLIENTS = "Clients";
    public static final String FIELD_ID = "id";
    public static final String FIELD_FIO = "fio";
    public static final String FIELD_TYPE_CLIENT = "type_client";
    public static final String FIELD_UNREAD_NOTIFICATIONS = "unread_notifications";
    public static final String QUERY_CREATE_TABLE_CLIENTS =
            "CREATE TABLE " + TABLE_CLIENTS + " ("
                    + FIELD_ID + " INTEGER, "
                    + FIELD_FIO + " TEXT NOT NULL, "
                    + FIELD_TYPE_CLIENT + " TEXT, "
                    + FIELD_UNREAD_NOTIFICATIONS + " INTEGER);";
    public static final String QUERY_DROP_TABLE_CLIENTS =
            "DROP TABLE IF EXISTS " + TABLE_CLIENTS + ";";
    public static final String QUERY_CLIENT_BY_ID =
            "SELECT * FROM " + TABLE_CLIENTS + " WHERE "
                    + FIELD_ID + " = ";

    public static final String TABLE_NOTIFICATION = "Notification";
    public static final String FIELD_ID_NOTIFICATION = "id_notification";
    public static final String FIELD_ID_CLIENT = "id_client";
    public static final String FIELD_ID_PRODUCT = "id_product";
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

    public static List<Client> readClients(Context context) {
        List<Client> clients = new ArrayList<>();

        DatabaseHelper helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        if (db == null)
            return clients;

        try {
            String[] columns = {
                    FIELD_ID,
                    FIELD_FIO,
                    FIELD_TYPE_CLIENT,
                    FIELD_UNREAD_NOTIFICATIONS
            };
            Cursor cursor = db.query(TABLE_CLIENTS, columns, null, null, null, null,
                    FIELD_UNREAD_NOTIFICATIONS);

            if (cursor == null)
                return clients;

            try {
                while (cursor.moveToNext()) {
                    Client client = new Client();
                    client.id = cursor.getInt(0);
                    client.fio = cursor.getString(1);
                    client.type = cursor.getString(2);
                    client.notifCount = cursor.getInt(3);
                    clients.add(client);
                }
            } finally {
                cursor.close();
            }
        } finally {
            db.close();
        }

        return clients;
    }

    public static void updateClients(Context context, List<Client> clients) {
        DatabaseHelper helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        if (db == null)
            return;

        try {
            db.execSQL(QUERY_DROP_TABLE_CLIENTS);

            for (Client client : clients) {
                ContentValues values = new ContentValues();
                values.put(FIELD_ID, client.id);
                values.put(FIELD_FIO, client.fio);
                values.put(FIELD_TYPE_CLIENT, client.type);
                values.put(FIELD_UNREAD_NOTIFICATIONS, client.notifCount);
                db.insertWithOnConflict(TABLE_CLIENTS, "", values, SQLiteDatabase.CONFLICT_IGNORE);
            }
        } finally {
            db.close();
        }
    }

    public static void addNotifications(Context context, List<Recommendation> recommendations) {
        DatabaseHelper helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        if (db == null)
            return;

        try {
            for (Recommendation rec : recommendations) {
                ContentValues notifValues = new ContentValues();
                notifValues.put(FIELD_ID_CLIENT, rec.client.id);
                notifValues.put(FIELD_TYPE_CLIENT, rec.client.type);
                notifValues.put(FIELD_ID_PRODUCT, rec.product.id);

                ContentValues clientValues = new ContentValues();
                clientValues.put(FIELD_ID_CLIENT, rec.client.id);
                clientValues.put(FIELD_FIO, rec.client.fio);
                clientValues.put(FIELD_TYPE_CLIENT, rec.client.type);

                Cursor cursor = db.rawQuery(
                        QUERY_CLIENT_BY_ID + rec.client.id
                                + " AND " + FIELD_TYPE_CLIENT + " = " + "'" + rec.client.type + "'", null);
                int oldCount = cursor.getInt(3);
                cursor.close();

                //старые нотификации + новые
                clientValues.put(FIELD_UNREAD_NOTIFICATIONS, oldCount + rec.client.notifCount);
                //Обновляем запись в базе клиентов
                db.update(
                        TABLE_CLIENTS,
                        clientValues,
                        FIELD_ID + " = ?" + " AND " + FIELD_TYPE_CLIENT + " = '?'",
                        new String[]{
                                String.valueOf(rec.product.id),
                                String.valueOf(rec.client.type)});
                db.insert(TABLE_NOTIFICATION, null, notifValues);
            }
        } finally {
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
            db.update(
                    TABLE_CLIENTS,
                    values,
                    FIELD_ID + " = " + clientId + " AND " + FIELD_TYPE_CLIENT + " = " + clientType,
                    null);
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
