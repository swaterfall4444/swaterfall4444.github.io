package com.example.ourfamilytree.database;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.ourfamilytree.R;
public class ListActivity extends AppCompatActivity {
    DBAdapter myDB;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        openDB();
        populateListView();

    }

    private void openDB() {
        myDB = new DBAdapter(this);
        myDB.open();
    }

    private void populateListView() {
        Cursor cursor = myDB.getAllRows();
        String[] fromFieldNames;
        if (cursor.getCount() != 0) {
            fromFieldNames = new String[]{
                    DBAdapter.ID, DBAdapter.NAME, DBAdapter.LOCATION, DBAdapter.IMAGE_URL};
        } else {
            fromFieldNames = new String[]{null, null, null, null};
        }

        int[] toViewIDs = new int[]{R.id.idNumber, R.id.nameResults, R.id.locationResults, R.id.displayImage};
        SimpleCursorAdapter myCursorAdapter;
        myCursorAdapter = new SimpleCursorAdapter(getBaseContext(), R.layout.listview_layout, cursor, fromFieldNames, toViewIDs, 0);
        ListView myList = findViewById(R.id.list_view);
        myList.setAdapter(myCursorAdapter);
    }
}



