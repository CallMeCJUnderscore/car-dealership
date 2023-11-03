package com.pluralsight;

import java.util.*;

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
        List<Vehicle> found = new ArrayList<>(inventory); //Generate temp table to perform filtering on
        if (min != -1) {found.removeIf(vehicle -> vehicle.getPrice()<min);} //If min is used, removes from results table if a given vehicle's price is below the min
        if (max != -1) {found.removeIf(vehicle -> vehicle.getPrice()>max);} //If max is used, removes from results table if a given vehicle's price is above the max

        if(found.isEmpty()){ //If no valid entries found, adds message informing such to results table
            found.add(new Vehicle(-1,-1,"ERROR", "NONE FOUND", "NONE", "NONE", -1, -1));
        }

        found.sort(Vehicle.comparePrice());
        return found;
    }
    public List<Vehicle> getVehiclesByMakeModel(String make, String model){
        List<Vehicle> found = new ArrayList<>(inventory); //Generate temp table to perform filtering on
        found.removeIf(vehicle -> !vehicle.getMake().equalsIgnoreCase(make)); //Removes vehicle from results table if not of given make
        found.removeIf(vehicle -> !vehicle.getModel().equalsIgnoreCase(model)); //Removes vehicle from results table if not of given model

        if(found.isEmpty()){ //If no valid entries found, adds message informing such to results table
            found.add(new Vehicle(-1,-1,"ERROR", "NONE FOUND", "NONE", "NONE", -1, -1));
        }

        found.sort(Vehicle.compareMakeModel());
        return found;
    }
    public List<Vehicle> getVehiclesByYear(int min, int max){
        List<Vehicle> found = new ArrayList<>(inventory); //Generate temp table to perform filtering on
        found.removeIf(vehicle -> vehicle.getYear()<min); //Removes vehicle from results table if made before given year
        found.removeIf(vehicle -> vehicle.getYear()>max); //Removes vehicle from results table if made after given year

        if(found.isEmpty()){ //If no valid entries found, adds message informing such to results table
            found.add(new Vehicle(-1,-1,"ERROR", "NONE FOUND", "NONE", "NONE", -1, -1));
        }

        found.sort(Vehicle.compareYear());
        return found;
    }
    public List<Vehicle> getVehiclesByColor(String color){
        List<Vehicle> found = new ArrayList<>(inventory); //Generate temp table to perform filtering on
        found.removeIf(vehicle -> !vehicle.getColor().equalsIgnoreCase(color));  //Removes vehicle from results table if not of given color

        if(found.isEmpty()){ //If no valid entries found, adds message informing such to results table
            found.add(new Vehicle(-1,-1,"ERROR", "NONE FOUND", "NONE", "NONE", -1, -1));
        }

        found.sort(Vehicle.compareColor());
        return found;
    }
    public List<Vehicle> getVehiclesByMileage(int min, int max){
        List<Vehicle> found = new ArrayList<>(inventory); //Generate temp table to perform filtering on
        found.removeIf(vehicle -> vehicle.getOdometer()<min);  //Removes vehicle from results table if has less miles than min
        found.removeIf(vehicle -> vehicle.getOdometer()>max);  //Removes vehicle from results table if has more miles than max

        if(found.isEmpty()){ //If no valid entries found, adds message informing such to results table
            found.add(new Vehicle(-1,-1,"ERROR", "NONE FOUND", "NONE", "NONE", -1, -1));
        }

        found.sort(Vehicle.compareMileage());
        return found;
    }
    public List<Vehicle> getVehiclesByType(String vehicleType){
        List<Vehicle> found = new ArrayList<>(inventory); //Generate temp table to perform filtering on
        found.removeIf(vehicle -> !vehicle.getVehicleType().equalsIgnoreCase(vehicleType));   //Removes vehicle from results table if not of given type

        if(found.isEmpty()){ //If no valid entries found, adds message informing such to results table
            found.add(new Vehicle(-1,-1,"ERROR", "NONE FOUND", "NONE", "NONE", -1, -1));
        }
        found.sort(Vehicle.compareMakeModel());
        return found;
    }
    public List<Vehicle> getAllVehicles(){
        inventory.sort(Vehicle.compareVIN());
        return inventory;
    }
    public void addVehicle(Vehicle vehicle){
        inventory.add(vehicle);
    }
    public void removeVehicle(Vehicle vehicle){
        inventory.remove(vehicle);
    }
}
