package com.example.ourfamilytree.database;


import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.ourfamilytree.R;


import java.sql.SQLException;

import static android.widget.Toast.LENGTH_SHORT;

public class Create_and_Update extends AppCompatActivity {

    TextView titleName;
    EditText uniqueIDBox;
    EditText nameBox;
    EditText informationBox;
    EditText sourceBox;
    EditText locationBox;
    EditText relativesBox;
    EditText imageBox;
    Button discardChanges;
    Button delete;
    Button update_Save;
    Button add;
    String UniqueID;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.basic_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.searchPage:
                Intent intent = new Intent(this, ListActivity.class);
                startActivity(intent);
                return true;
            case R.id.profile:
                Intent intent1 = new Intent(this, login.class);
                startActivity(intent1);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titleName = findViewById(R.id.ancestorName);
        uniqueIDBox = findViewById(R.id.uniqueID_field);
        nameBox = findViewById(R.id.newName);
        informationBox = findViewById(R.id.newInfo);
        sourceBox = findViewById(R.id.newSources);
        locationBox = findViewById(R.id.newLocation);
        relativesBox = findViewById(R.id.newRelatives);
        discardChanges = findViewById(R.id.discardChanges);
        delete = findViewById(R.id.Delete);
        update_Save = findViewById(R.id.saveChanges);
        add = findViewById(R.id.add);


      /*  discardChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ancestorView(uniqueIDBox.getText().toString());

            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAncestor();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addAncestor();
            }
        });
    }


    public void addAncestor(View view) {

        AncestorBaseHelper dbHelper = new AncestorBaseHelper(this, null, null, null, null, null, null, null, 1);
        Ancestor ancestor = new Ancestor(uniqueIDBox.getText().toString(), nameBox.getText().toString(), informationBox.getText().toString(), sourceBox.getText().toString(),
                locationBox.getText().toString(), relativesBox.getText().toString(), imageBox.getText().toString());


        dbHelper.addAncestor(ancestor);
        uniqueIDBox.setText("");
        nameBox.setText("");
        informationBox.setText("");
        sourceBox.setText("");
        locationBox.setText("");
        relativesBox.setText("");
        imageBox.setText("");

    }


    public void updateAncestor(View view) {

        if (nameBox.getText().toString().trim().length() <= 0) {
            Toast toast = Toast.makeText(getApplicationContext(), "The name field cannot be blank!", Toast.LENGTH_SHORT);
            toast.show();
        } else {

            try {
                Ancestor ancestor = new Ancestor(uniqueIDBox.getText().toString(), nameBox.getText().toString(), informationBox.getText().toString(), sourceBox.getText().toString(),
                        locationBox.getText().toString(), relativesBox.getText().toString(), imageBox.getText().toString());
                ;
                AncestorBaseHelper dbHelper = new AncestorBaseHelper(this, null, null, null, null, null, null, null, 1);

                if (ancestor != null) {
                    dbHelper.updateAncestor(ancestor);
                } else {
                    Toast.makeText(getApplicationContext(), "Unable to Update the ancestor", LENGTH_SHORT).show();
                }
            } catch (SQLiteConstraintException e) {
                Toast.makeText(getApplicationContext(), "There was an error trying to update that Ancestor", LENGTH_SHORT).show();
            }

        }
    }


        public void deleteAncestor (View view){


            if (result) {
                Toast.makeText(getApplicationContext(), "Ancestor Deleted", LENGTH_SHORT).show();
                uniqueIDBox.setText("");
                nameBox.setText("");
                informationBox.setText("");
                sourceBox.setText("");
                locationBox.setText("");
                relativesBox.setText("");

            } else {
                Toast.makeText(getApplicationContext(), "Ancestor Not found", LENGTH_SHORT).show();
            }
        }

        public void ancestorView (String UniqueID) {
            Intent intent = new Intent(Create_and_Update.this, AncestorView.class);
            intent.putExtra("Unique ID", UniqueID);
            startActivity(intent);
        }*/


    }
}





