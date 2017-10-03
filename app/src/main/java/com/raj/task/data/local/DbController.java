package com.raj.task.data.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.raj.task.data.DataSource;
import com.raj.task.data.User;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.raj.task.data.local.DbConstants.UserEntry.COLUMN_NAME_DOB;
import static com.raj.task.data.local.DbConstants.UserEntry.COLUMN_NAME_EMAIL;
import static com.raj.task.data.local.DbConstants.UserEntry.COLUMN_NAME_FIRST_NAME;
import static com.raj.task.data.local.DbConstants.UserEntry.COLUMN_NAME_ID;
import static com.raj.task.data.local.DbConstants.UserEntry.COLUMN_NAME_LAST_NAME;
import static com.raj.task.data.local.DbConstants.UserEntry.COLUMN_NAME_PHONE_NUMBER;
import static com.raj.task.data.local.DbConstants.UserEntry.TABLE_NAME;


/**
 * Concrete implementation of a data source as a db.
 */
public class DbController implements DataSource {

    private static DbController INSTANCE;

    private DbHelper mDbHelper;

    private DbController(@NonNull Context context) {
        checkNotNull(context);
        mDbHelper = new DbHelper(context);
    }

    public static DbController getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new DbController(context);
        }
        return INSTANCE;
    }

    @Override
    public void getUsers(@NonNull LoadUsersCallback callback) {
        List<User> users = new ArrayList<User>();
        SQLiteDatabase db = mDbHelper.getReadableDatabase();


        Cursor c = db.query(
                TABLE_NAME, null, null, null, null, null, null);

        if (c != null && c.getCount() > 0) {
            while (c.moveToNext()) {
                User user = new User();
                user.setId(c.getLong(c.getColumnIndexOrThrow(COLUMN_NAME_ID)));
                user.setfName(c.getString(c.getColumnIndexOrThrow(COLUMN_NAME_FIRST_NAME)));
                user.setlName(c.getString(c.getColumnIndexOrThrow(COLUMN_NAME_LAST_NAME)));
                user.setEmail(c.getString(c.getColumnIndexOrThrow(COLUMN_NAME_EMAIL)));
                user.setPhoneNumber(c.getString(c.getColumnIndexOrThrow(COLUMN_NAME_PHONE_NUMBER)));
                user.setDob(c.getLong(c.getColumnIndexOrThrow(COLUMN_NAME_DOB)));

                users.add(user);

            }
        }
        if (c != null) {
            c.close();
        }

        db.close();

        if (users.isEmpty()) {
            callback.onDataNotAvailable();
        } else {
            callback.onUserLoaded(users);
        }
    }

    @Override
    public void saveUser(@NonNull User user) {
        checkNotNull(user);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_FIRST_NAME, user.getfName());
        values.put(COLUMN_NAME_LAST_NAME, user.getlName());
        values.put(COLUMN_NAME_EMAIL, user.getEmail());
        values.put(COLUMN_NAME_PHONE_NUMBER, user.getPhoneNumber());
        values.put(COLUMN_NAME_DOB, user.getDob());

        long id = db.insert(TABLE_NAME, null, values);

        db.close();
    }

    @Override
    public void updateUser(@NonNull User user) {
        checkNotNull(user);

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        String whereClause = COLUMN_NAME_ID + " = ?";
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_FIRST_NAME, user.getfName());
        values.put(COLUMN_NAME_LAST_NAME, user.getlName());
        values.put(COLUMN_NAME_EMAIL, user.getEmail());
        values.put(COLUMN_NAME_PHONE_NUMBER, user.getPhoneNumber());
        values.put(COLUMN_NAME_DOB, user.getDob());

        // db.insert(TABLE_NAME, null, values);

        db.update(TABLE_NAME, values, whereClause, new String[]{String.valueOf(user.getId())});
        db.close();
    }

    @Override
    public void deleteUser(@NonNull long id) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        String whereClause = COLUMN_NAME_ID + " = ?";
        db.delete(TABLE_NAME, whereClause, new String[]{String.valueOf(id)});
        db.close();
    }

}
