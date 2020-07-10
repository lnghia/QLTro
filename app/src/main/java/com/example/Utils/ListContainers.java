package com.example.Utils;

import com.example.Models.Contract.Contract;
import com.example.Models.Customer.Customer;
import com.example.Models.Facility.Facility;
import com.example.Models.Room.Room;

import java.util.ArrayList;

public class ListContainers {
    private static ListContainers instance;
    private ArrayList<Facility> facilities;
    private ArrayList<Customer> customers;
    private ArrayList<Room> rooms;
    private ArrayList<Contract> contracts;
    private String token;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public ArrayList<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(ArrayList<Contract> contracts) {
        this.contracts = contracts;
    }
}
