package com.example.ourfamilytree.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class Ancestor {

    private static Ancestor sAncestors;

    private List<Ancestor> mAncestors;
    private Context mContext;
    private SQLiteDatabase mDatabase;
    private int id;


   private Ancestor(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new AncestorBaseHelper(mContext, null,null, null, null, null,  null,null,1)
                .getWritableDatabase();
        mAncestors = new ArrayList<>();
    }
    //fields
    private int ID;
    private String Unique_ID;
    private String Name;
    private String Relatives;
    private String Location;
    private String Source;
    private String Information;
    private String Image;

    //Constructors
    //Empty Constructor
    public Ancestor() {}

    //Constructor with variables
    public Ancestor(int id, String unique_ID, String name, String information, String source, String location, String relatives, String image) {
        this.ID = id;
        this.Unique_ID = unique_ID;
        this.Name = name;
        this.Information = information;
        this.Source = source;
        this.Location  = location;
        this.Relatives = relatives;
        this.Image = image;
    }

    //Constructor without ID
    public Ancestor(String unique_ID, String name, String information, String source, String location, String relatives, String image) {
        this.Unique_ID = unique_ID;
        this.Name = name;
        this.Information = information;
        this.Source = source;
        this.Location = location;
        this.Relatives = relatives;
        this.Image = image;
    }

    //properties
    //ID
    public void setID(int id) {
        this.ID = id;
    }
    public int getID() {
        return this.ID;
    }

    //User Selected Unique ID
    public void setUnique_ID(String unique_ID){
        this.Unique_ID = unique_ID;
    }
    public String getUnique_ID(){
        return this.Unique_ID;
    }

    //Name
    public void setName (String name) {
        this.Name = name;
    }
    public String getName(){
        return this.Name;
    }


    //Information

    public void setInformation(String information){
        this.Information = information;
    }
    public String getInformation () {
        return this.Information;
    }

    //Sources

    public void setSource (String source) {
        this.Source = source;
    }
    public String getSource () {
        return this.Source;
    }

    //Location
    public void setLocation (String location){
        this.Location = location;
    }
    public String getLocation (){
        return this.Location;
    }

    //Relatives
    public void setRelatives (String relatives){
        this.Relatives = relatives;
    }
    public String getRelatives(){
        return this.Relatives;
    }

    //Image
    public void setImage(String image){
        this.Image = image;
    }
    public String getImage(){
        return this.Image;
    }
}
