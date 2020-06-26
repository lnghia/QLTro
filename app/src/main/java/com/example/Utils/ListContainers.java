package com.example.Utils;

import com.example.Models.Facility.Facility;

import java.util.ArrayList;

public class ListContainers {
    private static ListContainers instance;
    private ArrayList<Facility> facilities;

    public ListContainers(){
        facilities=new ArrayList<>();
    }

    public static synchronized ListContainers getInstance(){
        if(instance==null){
            instance=new ListContainers();
        }

        return instance;
    }

    public ArrayList<Facility> getFacilities() {
        return facilities;
    }

    public void setFacilities(ArrayList<Facility> facilities) {
        this.facilities = facilities;
    }
}
