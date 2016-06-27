package com.example.a00584083.db_exercise_03_11_2016;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ContactDatabaseHelper {

    private static final String TAG = ContactDatabaseHelper.class.getSimpleName();

    // database configuration
    // if you want the onUpgrade to run then change the database_version
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "contact_database.db";

    // table configuration
    private static final String TABLE_NAME = "contacts";         // Table name
    private static final String COLUMN_ID = "_id";     // a column named "_id" is required for cursor
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PHONE_NUMBER = "phone_number";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_STREET_ADDRESS = "street_address";
    private static final String COLUMN_CITY = "city";
    private static final String COLUMN_PROVINCE = "province";
    private static final String COLUMN_POSTAL_CODE = "postal_code";

    private DatabaseOpenHelper openHelper;
    private SQLiteDatabase database;

    // this is a wrapper class. that means, from outside world, anyone will communicate with PersonDatabaseHelper,
    // but under the hood actually DatabaseOpenHelper class will perform database CRUD operations
    public ContactDatabaseHelper(Context aContext) {
        openHelper = new DatabaseOpenHelper(aContext);
        database = openHelper.getWritableDatabase();
    }

    public void insertData (String name, String phoneNumber, String email, int streetAddress,
                            String city, String province, String postalCode) {

        // we are using ContentValues to avoid sql format errors

        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_PHONE_NUMBER, phoneNumber);
        contentValues.put(COLUMN_EMAIL, email);
        contentValues.put(COLUMN_STREET_ADDRESS, streetAddress);
        contentValues.put(COLUMN_CITY, city);
        contentValues.put(COLUMN_PROVINCE, province);
        contentValues.put(COLUMN_POSTAL_CODE, postalCode);

        database.insert(TABLE_NAME, null, contentValues);
        System.out.print(contentValues);
    }

    public int deleteRow(String id) {
        return database.delete(TABLE_NAME, COLUMN_ID + "=" + id, null);
    }

    public int updateRow(String id, String name, String phoneNumber, String email,
                         int streetAddress, String city, String province, String postalCode) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_PHONE_NUMBER, phoneNumber);
        cv.put(COLUMN_EMAIL, email);
        cv.put(COLUMN_STREET_ADDRESS, streetAddress);
        cv.put(COLUMN_CITY, city);
        cv.put(COLUMN_PROVINCE, province);
        cv.put(COLUMN_POSTAL_CODE, postalCode);
        return database.update(TABLE_NAME, cv, COLUMN_ID + "=" + id, null);
    }


    public Cursor getAllData () {

        String buildSQL = "SELECT * FROM " + TABLE_NAME;

        Log.d(TAG, "getAllData SQL: " + buildSQL);

        return database.rawQuery(buildSQL, null);
    }

    // this DatabaseOpenHelper class will actually be used to perform database related operation

    private class DatabaseOpenHelper extends SQLiteOpenHelper {

        public DatabaseOpenHelper(Context aContext) {
            super(aContext, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            // Create your tables here

            String buildSQL = "CREATE TABLE " + TABLE_NAME + "( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT NOT NULL, " + COLUMN_EMAIL + " TEXT NOT NULL, " + COLUMN_STREET_ADDRESS + " INTEGER NOT NULL, " +
            COLUMN_CITY + " TEXT NOT NULL, " + COLUMN_PROVINCE + " TEXT NOT NULL, " + COLUMN_POSTAL_CODE + " TEXT NOT NULL, " + COLUMN_PHONE_NUMBER + " TEXT NOT NULL);";
            System.out.println(buildSQL);

            Log.d(TAG, "onCreate SQL: " + buildSQL);

            sqLiteDatabase.execSQL(buildSQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
            // Database schema upgrade code goes here

            String buildSQL = "DROP TABLE IF EXISTS " + TABLE_NAME;

            Log.d(TAG, "onUpgrade SQL: " + buildSQL);

            sqLiteDatabase.execSQL(buildSQL);       // drop previous table

            onCreate(sqLiteDatabase);               // create the table from the beginning

        }
    }
}
