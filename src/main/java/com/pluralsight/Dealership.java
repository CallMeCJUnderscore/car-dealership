package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Dealership {

    /*---------------VARIABLES---------------*/

    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;

    /*--------------CONSTRUCTORS-------------*/

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        inventory = new ArrayList<>();
    }

    /*------------GETTERS/SETTERS------------*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /*---------------FUNCTIONS---------------*/

    public List<Vehicle> getVehiclesByPrice(double min, double max){
        return null;
    }
    public List<Vehicle> getVehiclesByMakeModel(String make, String model){
        return null;
    }
    public List<Vehicle> getVehiclesByYear(int min, int max){
        return null;
    }
    public List<Vehicle> getVehiclesByColor(String color){
        return null;
    }
    public List<Vehicle> getVehiclesByMileage(int min, int max){
        return null;
    }
    public List<Vehicle> getVehiclesByType(String vehicleType){
        return null;
    }
    public List<Vehicle> getAllVehicles(){
        return inventory;
    }
    public void addVehicle(Vehicle vehicle){
        inventory.add(vehicle);
    }
    public void removeVehicle(Vehicle vehicle){

    }
}
