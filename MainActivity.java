package com.example.ourfamilytree.database;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ourfamilytree.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final int ERROR_DIALOG_REQUEST = 9001;

    TextView idView;
    EditText nameBox;
    EditText informationBox;
    EditText sourceBox;
    EditText locationBox;
    EditText relativesBox;
    Button userPrefs;
    Button ancestorView;
    Button buttonMap;
    int length;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idView = findViewById(R.id.AncestorID);
        nameBox = findViewById(R.id.Name);
        informationBox = findViewById(R.id.Information);
        sourceBox = findViewById(R.id.Source);
        locationBox = findViewById(R.id.Location);
        relativesBox = findViewById(R.id.Relatives);
        userPrefs = (Button) findViewById(R.id.userPrefs);
        buttonMap = (Button) findViewById(R.id.buttonMap);
        ancestorView = findViewById(R.id.ancestorView);

        userPrefs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userPreferences();
            }


        });
        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initializeMap();

            }
        });
        length = nameBox.getText().toString().trim().length();

        hideSoftKeyboard();





        ancestorView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ancestorView();

            }
        });
    }



    public boolean isServicesOK(){
        Log.d(TAG, "isServicesOK: checking google services version");
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);

        if(available == ConnectionResult.SUCCESS) {
            //user can make map requests
            Log.d(TAG, "isServiceOK: Google Play Services is working");
            return true;
        }
        else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //resolvable error occurred
            Log.d(TAG, "isServicesOK: an error has occurred but it can be fixed");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }
        else{
            Toast.makeText(this, "You can't make map requests", LENGTH_SHORT).show();
        }
        return false;

    }

    public void addAncestor(View view) {

        AncestorBaseHelper dbHelper = new AncestorBaseHelper(this, null, null, null, null, null, null, 1);
        Ancestor ancestor = new Ancestor(nameBox.getText().toString(), informationBox.getText().toString(), sourceBox.getText().toString(),
                locationBox.getText().toString(), relativesBox.getText().toString());


        dbHelper.addAncestor(ancestor);
        nameBox.setText("");
        informationBox.setText("");
        sourceBox.setText("");
        locationBox.setText("");
        relativesBox.setText("");

    }


    public void searchAncestor(View view) {
        Ancestor ancestor;
        AncestorBaseHelper dbHelper = new AncestorBaseHelper(this, null, null, null, null, null, null, 1);
        ancestor = dbHelper.searchAncestor(nameBox.getText().toString());



        if (ancestor != null) {
            idView.setText(String.valueOf(ancestor.getID()));
            nameBox.setText(String.valueOf(ancestor.getName()));
            informationBox.setText(String.valueOf(ancestor.getInformation()));
            sourceBox.setText(String.valueOf(ancestor.getSource()));
            locationBox.setText(String.valueOf(ancestor.getLocation()));
            relativesBox.setText(String.valueOf(ancestor.getRelatives()));
        } else {
            idView.setText("Ancestor not found.");
        }


    }


    public void deleteAncestor(View view) {
        AncestorBaseHelper dbHelper = new AncestorBaseHelper(this, null, null, null, null, null, null, 1);
        boolean result = dbHelper.deleteAncestor(nameBox.getText().toString());

        if (result) {
            idView.setText("Ancestor Deleted");
            nameBox.setText("");
            informationBox.setText("");
            sourceBox.setText("");
            locationBox.setText("");
            relativesBox.setText("");

        } else {
            idView.setText("Ancestor not found");
        }
    }
    public void userPreferences(){
        Intent intent = new Intent(this, userPreferences.class);
        startActivity(intent);
    }

    public void initializeMap(){
        Intent intent = new Intent(MainActivity.this, MapActivity.class);
        startActivity(intent);


    }

    public void ancestorView() {
        if (nameBox.getText().toString().trim().length() <= 0) {
            Toast toast = Toast.makeText(getApplicationContext(), "The name field cannot be blank!", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            Intent intent = new Intent(MainActivity.this, AncestorView.class);
            intent.putExtra("Name", nameBox.getText().toString());
            startActivity(intent);

        }
    }


    //automatically hide the keyboard
    private void hideSoftKeyboard(){
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}




