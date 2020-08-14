package com.example.ourfamilytree.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.ContentObservable;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AncestorBaseHelper extends SQLiteOpenHelper {

    //information of the database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ancestorDB.db";

    //Table
    private static final String TABLE_ANCESTORS = "ancestors";

    //Columns
    private static final String ID = "id";

    private static final String NAME = "name";
    private static final String INFORMATION = "information";
    private static final String SOURCE = "source";
    private static final String LOCATION = "location";
    private static final String RELATIVES = "relatives";
    private static final String IMAGE_URL = "imageURL";

    //constructor
    public AncestorBaseHelper(Context context, String unique_id, String name, String information, String source, String location, String relatives, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_ANCESTORS_TABLE = "CREATE TABLE " + TABLE_ANCESTORS +
             "(" + ID + "INTEGER PRIMARY KEY," + NAME + " TEXT NOT NULL," + INFORMATION
                     + " TEXT," + SOURCE + " TEXT," + LOCATION
                     + " TEXT," + RELATIVES + " TEXT" + IMAGE_URL + "TEXT" + ")";
        db.execSQL(CREATE_ANCESTORS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ANCESTORS);
        onCreate(db);

    }

     public void addAncestor(Ancestor ancestor) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME, ancestor.getName());
        values.put(INFORMATION, ancestor.getInformation());
        values.put(SOURCE, ancestor.getSource());
        values.put(LOCATION, ancestor.getLocation());
        values.put(RELATIVES, ancestor.getRelatives());
        values.put(IMAGE_URL,ancestor.getImage());

        db.insert(TABLE_ANCESTORS, null, values);
        db.close();
    }
    public Ancestor viewData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ANCESTORS, null);
        Ancestor ancestor = new Ancestor();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            ancestor.setID(Integer.parseInt(cursor.getString(0)));
            ancestor.setUnique_ID(cursor.getString(1));
            ancestor.setName(cursor.getString(2));
            ancestor.setInformation(cursor.getString(3));
            ancestor.setSource(cursor.getString(4));
            ancestor.setLocation(cursor.getString(5));
            ancestor.setRelatives(cursor.getString(6));
            ancestor.setImage(cursor.getString(7));
            cursor.close();
        } else {
            ancestor = null;
        }
        db.close();

        return ancestor;
    }

    public Ancestor searchAncestor(String Name) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ANCESTORS + " WHERE " + NAME + " =? ",
            new String[]{Name});
            Ancestor ancestor = new Ancestor();
            if (cursor.moveToFirst()) {
                cursor.moveToFirst();
                ancestor.setID(Integer.parseInt(cursor.getString(0)));
                ancestor.setName(cursor.getString(1));
                ancestor.setInformation(cursor.getString(2));
                ancestor.setSource(cursor.getString(3));
                ancestor.setLocation(cursor.getString(4));
                ancestor.setRelatives(cursor.getString(5));
                ancestor.setImage(cursor.getString(6));
                cursor.close();
            } else {
                ancestor = null;
            }
            db.close();

        return ancestor;
    }


}
