package com.android.rolap.Rest.Response;

public class Building {

    private String id;
    private String name;

    public Building(String id, String name){
        this.id = id;
        this.name = name;
    }

    public String getId(){
        return id;
    }
    public String getName(){
        return name;
    }
}
