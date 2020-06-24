package com.example.Utils;

public class ListContainers {
    private static ListContainers instance;

    public static synchronized ListContainers getInstance(){
        if(instance==null){
            instance=new ListContainers();
        }

        return instance;
    }
}
