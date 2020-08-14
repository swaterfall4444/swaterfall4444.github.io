package com.example.ourfamilytree.database;

public class AncestorListItems {
    private static String url;
    private static int id;
    private static String name;
    private static String location;



    public static int getID() {
        return id;
    }
    public static void setId(int id){
        id = AncestorListItems.id;
    }
    public static String getName() {
        return name;
    }
    public static void setName(String name){
        AncestorListItems.name = AncestorListItems.name;
    }

    public static String getLocation() {
        return location;
    }
    public static void setLocation(String location){
        AncestorListItems.location = AncestorListItems.location;
    }
    public static String getUrl() {
        return url;
    }
    public static void setUrl(){
        url = AncestorListItems.url;
    }
}
