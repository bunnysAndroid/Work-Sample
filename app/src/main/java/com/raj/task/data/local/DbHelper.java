
package com.raj.task.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "Users.db";

    private static final String TEXT_TYPE = " TEXT";

    private static final String INTEGER_TYPE = " INTEGER";

    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DbConstants.UserEntry.TABLE_NAME + " (" +
                    DbConstants.UserEntry.COLUMN_NAME_ID + INTEGER_TYPE + " PRIMARY KEY," +
                    DbConstants.UserEntry.COLUMN_NAME_FIRST_NAME + TEXT_TYPE + COMMA_SEP +
                    DbConstants.UserEntry.COLUMN_NAME_LAST_NAME + TEXT_TYPE + COMMA_SEP +
                    DbConstants.UserEntry.COLUMN_NAME_EMAIL + TEXT_TYPE + COMMA_SEP +
                    DbConstants.UserEntry.COLUMN_NAME_PHONE_NUMBER + TEXT_TYPE + COMMA_SEP +
                    DbConstants.UserEntry.COLUMN_NAME_DOB + INTEGER_TYPE +
                    " )";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
