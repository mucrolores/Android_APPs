package com.example.mucolores.db_2019;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "database";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "Demo";
    private static final String NAME_COL = "name";
    private static final String DESCRIPTION_COL = "description";

    public DataBaseHelper(Context context)
    {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        upDataMyDataBase(db,0,DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        upDataMyDataBase(db,oldVersion,newVersion);
    }

    private void upDataMyDataBase(SQLiteDatabase sqLiteDatabase,int oldVersion,int newVersion)
    {
        if(newVersion>oldVersion)
        {
            sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NAME_COL + " VARCHAR, "
                + DESCRIPTION_COL + " VARCHAR);");
            insert(sqLiteDatabase,"Latte","Espresso and steamed milk");
            insert(sqLiteDatabase,"Cappuccino","Espresso,hot milk and steamed-milk foam");

        }
    }

    private static void insert(SQLiteDatabase sqLiteDatabase,String name,String description)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME_COL,name);
        contentValues.put(DESCRIPTION_COL,description);
        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
    }
}
