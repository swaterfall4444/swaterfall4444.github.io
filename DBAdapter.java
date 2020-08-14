package com.example.ourfamilytree.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ImageView;

public class DBAdapter {

        private static final String TAG = "DBAdapter";

        //Field Name
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String INFORMATION = "information";
        public static final String SOURCE = "source";
        public static final String LOCATION = "location";
        public static final String RELATIVES = "relatives";
        public static final String IMAGE_URL = "imageURL";


        public static final String[] ALL_KEYS = new String[] {ID, NAME, INFORMATION, SOURCE, LOCATION, RELATIVES, IMAGE_URL};

        //Column numbers for each field name
        public static final int COL_ROWID = 0;
        public static final int COL_NAME = 1;
        public static final int COL_INFORMATION = 2;
        public static final int COL_SOURCE = 3;
        public static final int COL_LOCATION = 4;
        public static final int COL_RELATIVES = 5;
        public static final int COL_IMAGE = 6;

        //Database info
        private static final String DATABASE_NAME = "ancestorDB.db";
        private static final int DATABASE_VERSION = 1;
        //Table
        private static final String TABLE_ANCESTORS = "ancestors";

        //SQL statement to create database
        private static final String DATABASE_CREATE_SQL =
                "CREATE TABLE " + TABLE_ANCESTORS
                        + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + NAME + " TEXT NOT NULL, "
                        + INFORMATION + " TEXT,"
                        + SOURCE + " TEXT,"
                        + LOCATION + " TEXT,"
                        + RELATIVES + " TEXT,"
                        + IMAGE_URL + " TEXT"
                        + ")";

        private final Context context;
        private DatabaseHelper myDbHelper;
        private SQLiteDatabase db;

        public DBAdapter(Context ctx){
            this.context = ctx;
            myDbHelper = new DatabaseHelper(context);
        }

        //Open the database connection
        public DBAdapter open(){
            db = myDbHelper.getWritableDatabase();
            return this;
        }

        //Close the database connection
        public void close(){
            myDbHelper.close();
        }

        //Add a new set of values to be inserted into  the database
        public long insertRow(Ancestor ancestor){
            ContentValues initialValues = new ContentValues();
            ContentValues values = new ContentValues();
            values.put(NAME, ancestor.getName());
            values.put(INFORMATION, ancestor.getInformation());
            values.put(SOURCE, ancestor.getSource());
            values.put(LOCATION, ancestor.getLocation());
            values.put(RELATIVES, ancestor.getRelatives());
            values.put(IMAGE_URL,ancestor.getImage());

            //Insert the data into the database
            return db.insert(TABLE_ANCESTORS, null, initialValues);
        }

        //Delete a row from the database, rowId (primary key)
        public boolean deleteRow(long rowId){
            String where = ID + "=" + rowId;
            return db.delete(TABLE_ANCESTORS,
                    where, null) != 0;
        }

        //Delete the database
        public void deleteAll(){
            Cursor c = getAllRows();
            long rowId = c.getColumnIndexOrThrow(ID);
            if(c.moveToFirst()){
                do{
                    deleteRow(c.getLong((int) rowId));
                } while (c.moveToNext());
            }
            c.close();
        }

        //Return all data in the database
        public Cursor getAllRows(){
            String where = null;
            Cursor c = db.query(true, TABLE_ANCESTORS, ALL_KEYS, where, null, null, null, null, null);
            if (c != null){
                c.moveToFirst();
            }
            return c;
        }

        //Change an existing row to be equal to new data
        public boolean updateRow(long rowId, Ancestor ancestor){
            String where = ID + "=" + rowId;
            ContentValues newValues = new ContentValues();
            ContentValues values = new ContentValues();

            values.put(NAME, ancestor.getName());
            values.put(INFORMATION, ancestor.getInformation());
            values.put(SOURCE, ancestor.getSource());
            values.put(LOCATION, ancestor.getLocation());
            values.put(RELATIVES, ancestor.getRelatives());
            values.put(IMAGE_URL, ancestor.getImage());

            //Insert it into the database
            return db.update(TABLE_ANCESTORS, newValues, where, null) != 0;
        }

        public static class DatabaseHelper extends SQLiteOpenHelper {

            DatabaseHelper(Context context) {
                super(context, DATABASE_NAME, null, DATABASE_VERSION);
            }

            @Override
            public void onCreate(SQLiteDatabase _db) {
                _db.execSQL(DATABASE_CREATE_SQL);
            }

            @Override
            public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
                Log.w(TAG, "Upgrading application's database from version" + oldVersion
                        + " to " + newVersion + ", which will destroy all old data!");

                //Destroy old database:
                _db.execSQL("DROP TABLE IF EXISTS " + TABLE_ANCESTORS);

                //Recreate new database:
                onCreate(_db);
            }

        }
}


