package com.example.ourfamilytree.database;


import android.app.Dialog;
import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ourfamilytree.R;
import com.facebook.CallbackManager;

import com.facebook.FacebookSdk;

import com.facebook.share.model.ShareLinkContent;

import com.facebook.share.widget.ShareDialog;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.squareup.picasso.Picasso;

import static android.widget.Toast.LENGTH_SHORT;


public class AncestorView extends AppCompatActivity {
    private static final String TAG = "AncestorView";
    private static final int ERROR_DIALOG_REQUEST = 9001;

    ImageView Image;
    TextView Name;
    TextView Location;
    TextView KnownRelatives;
    TextView Information;
    Button Share;
    Button buttonMap;
    Button editButton;

    String location;
    String id;
    String url;
    static String country;

    //Facebook SDK variables
    ShareDialog shareDialog;
    CallbackManager callbackManager;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.family_tree, menu);
        return true;
    }

    //Move pages based on user selected option
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                Intent intent = new Intent(this, ListActivity.class);
                startActivity(intent);
                return true;
            case R.id.add:
                Intent intent1 = new Intent(this, Create_and_Update.class);
                startActivity(intent1);
                return true;
            case R.id.profile:
                Intent intent2 = new Intent(this, login.class);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //initializing Facebook SDK
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_ancestor_view);

        Image = findViewById(R.id.imageView);
        Name = findViewById(R.id.Name);
        Location = findViewById(R.id.Location);
        KnownRelatives = findViewById(R.id.KnownRelatives);
        Information = findViewById(R.id.Information);
        Share = findViewById(R.id.Share);
        buttonMap = findViewById(R.id.buttonMap);
        editButton = findViewById(R.id.edit);

        //Facebook variables initialized
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);

        getAncestor();

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateAncestor();
            }
        });

        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initializeMap();

            }
        });

        //Set up share button to link to app, temporarily used ancestry.com link since this app is not on the playstore.
        Share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareLinkContent linkContent = new ShareLinkContent.Builder()
                        .setQuote("Check out your ancestors!").setContentUrl(Uri.parse("https://ancestry.com")).build();
                if (ShareDialog.canShow(ShareLinkContent.class)) {
                    shareDialog.show(linkContent);
                }
            }
        });

    }


        //verify that Google Play Services are available
        public boolean isServicesOK(){
            Log.d(TAG, "isServicesOK: checking google services version");
            int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(AncestorView.this);

            if(available == ConnectionResult.SUCCESS) {
                //user can make map requests
                Log.d(TAG, "isServiceOK: Google Play Services is working");
                return true;
            }
            else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)){
                //resolvable error occurred
                Log.d(TAG, "isServicesOK: an error has occurred but it can be fixed");
                Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(AncestorView.this, available, ERROR_DIALOG_REQUEST);
                dialog.show();
            }
            else{
                Toast.makeText(this, "You can't make map requests", LENGTH_SHORT).show();
            }
            return false;

        }
    public void initializeMap(){
        Intent intent = new Intent(AncestorView.this, MapActivity.class);
        startActivity(intent);
    }

    //facebook activity result call
    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
    public void getAncestor(){
        Ancestor ancestor;
        id = getIntent().getStringExtra("id");
        AncestorBaseHelper dbHelper = new AncestorBaseHelper(this, null, null,null, null, null, null, null, 1);
        ancestor = dbHelper.searchAncestor(id);

        if(ancestor != null){
            Name.setText(String.valueOf(ancestor.getName()));
            Location.setText(String.valueOf(ancestor.getLocation()));
            KnownRelatives.setText(String.valueOf(ancestor.getRelatives()));
            Information.setText(String.valueOf(ancestor.getInformation()));
            url = String.valueOf(ancestor.getImage());


        }
        Picasso.get().load(url).resize(50,50).into(Image);



    }
    public void updateAncestor(){
        Intent  intent = new Intent(AncestorView.this, Create_and_Update.class);
        intent.putExtra("id", id);
        startActivity(intent);

    }



}
