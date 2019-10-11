package vetzwe.animaldiseases.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DatabaseObject {

    private static Database dbHelper;
    private SQLiteDatabase db;

    public DatabaseObject(Context paramContext) {
        dbHelper = new Database(paramContext);


        dbHelper.getWritableDatabase();
        this.db = dbHelper.getReadableDatabase();
    }

    public void closeDbConnection() {
        if (this.db != null) {
            this.db.close();
        }
    }

    public SQLiteDatabase getDbConnection() {
        return this.db;
    }

    public void upgradeDb(int version) {
        dbHelper.setForcedUpgrade(version);
        Log.v("Database Upgrade", "upgrade successful");
    }
}