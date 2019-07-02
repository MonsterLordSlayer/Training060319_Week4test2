package com.example.week4test;

import java.util.Locale;

public class DatabaseContract {
    public static final String DATABASE_NAME = "coffedatabase2";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "coffe_table";
    public static final String FIELD_DESC = "desc";
    public static final String FIELD_URL = "url";
    public static final String FIELD_ID = "id";
    public static final String FIELD_NAME = "name";


    public static String getCreateTableStatement() {
        return String.format(
                Locale.US,
                "CREATE TABLE %s(%s TEXT PRIMARY_KEY, %s TEXT, %s TEXT, %s TEXT)",
                TABLE_NAME,FIELD_ID, FIELD_DESC, FIELD_URL, FIELD_NAME);
    }

    public static String getSelectOneCoffeItem(String title) {
        return String.format(Locale.US, "SELECT * FROM %s WHERE %s = \"%s\"", TABLE_NAME, FIELD_ID, title);
    }

    public static String getSelectAllCoffeItems() {
        return String.format(Locale.US, "SELECT * FROM %s", TABLE_NAME);
    }

    public static String whereClauseForUpdate(String title) {
        return String.format(Locale.US, "WHERE %s = %s", FIELD_ID, title);
    }

}
