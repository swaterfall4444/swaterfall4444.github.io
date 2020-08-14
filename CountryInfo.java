package com.example.ourfamilytree.database;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ourfamilytree.R;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;


public class CountryInfo extends AppCompatActivity {

    String country;
    TextView Header, countryInfo;
    String responseString;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_info);
        Header = findViewById(R.id.headercountryinfo);
        countryInfo = findViewById(R.id.countryInfo);


        country = AncestorView.country;

        //I can't seem to get this to return a value for my text view any help would be greatly appreciated
        try {
            final HttpResponse<String> response = Unirest.get("https://restcountries-v1.p.rapidapi.com/name/" + country)
                    .header("x-rapidapi-host", "restcountries-v1.p.rapidapi.com")
                    .header("x-rapidapi-key", "62084568a2msh7d884f01fe23040p11ead1jsn44b91b21d367")
                    .asString();
            responseString = response.getBody();
            countryInfo.setText(responseString);


        } catch (UnirestException e) {
            e.printStackTrace();
        }

    }

}
