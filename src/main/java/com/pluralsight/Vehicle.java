package com.pluralsight;

import java.util.Comparator;

public class Vehicle {


    /*---------------VARIABLES---------------*/

    private int vin;
    private int year;
    private String make;
    private String model;
    private String vehicleType;
    private String color;
    private int odometer;
    private double price;

    /*--------------CONSTRUCTORS-------------*/

    public Vehicle(int vin, int year, String make, String model, String vehicleType, String color, int odometer, double price) {
        this.vin = vin;
        this.year = year;
        this.make = make;
        this.model = model;
        this.vehicleType = vehicleType;
        this.color = color;
        this.odometer = odometer;
        this.price = price;
    }

    /*------------GETTERS/SETTERS------------*/

    public int getVin() {
        return vin;
    }

    public void setVin(int vin) {
        this.vin = vin;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /*---------------FUNCTIONS---------------*/

    public static Comparator<Vehicle> compareYear(){
        return Comparator.comparing(Vehicle::getYear)
                .thenComparing(Vehicle::getVin);
    }
    public static Comparator<Vehicle> compareColor(){
        return Comparator.comparing(Vehicle::getColor)
                .thenComparing(Vehicle::getVin);
    }
    public static Comparator<Vehicle> comparePrice(){
        return Comparator.comparing(Vehicle::getPrice)
                .thenComparing(Vehicle::getVin);
    }
    public static Comparator<Vehicle> compareMileage(){
        return Comparator.comparing(Vehicle::getOdometer)
                .thenComparing(Vehicle::getVin);
    }

    public static Comparator<Vehicle> compareMakeModel(){
        return Comparator.comparing(Vehicle::getMake)
                .thenComparing(Vehicle::getModel)
                .thenComparing(Vehicle::getVin);
    }

    public static Comparator<Vehicle> compareVIN(){
        return Comparator.comparing(Vehicle::getVin);
    }
}
