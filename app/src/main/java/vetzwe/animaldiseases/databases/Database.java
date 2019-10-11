package vetzwe.animaldiseases.databases;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class Database extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "data.db";
    private static final int DATABASE_VERSION = 1;

    public Database(Context paramContext) {
        super(paramContext, DATABASE_NAME, null, DATABASE_VERSION);
    }
}