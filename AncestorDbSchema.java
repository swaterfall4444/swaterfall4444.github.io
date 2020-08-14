package com.example.ourfamilytree.database;

public class AncestorDbSchema {
    public static final class AncestorTable {
        public static final String NAME = "ancestors";

        public static final class Cols {
            public static final int ID = 0;
            public static final String NAME = "name";
            public static final String INFORMATION = "information";
            public static final String SOURCE = "source";
            public static final String LOCATION = "location";
            public static final String RELATIVES = "relatives";
            public static final String IMAGEURL="imageURL";

        }
    }
}
