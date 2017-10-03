package com.raj.task.data.local;

import android.provider.BaseColumns;

public final class DbConstants {


    public static abstract class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_FIRST_NAME = "fName";
        public static final String COLUMN_NAME_LAST_NAME = "lName";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_PHONE_NUMBER = "phoneNumber";
        public static final String COLUMN_NAME_DOB = "dob";

    }
}
