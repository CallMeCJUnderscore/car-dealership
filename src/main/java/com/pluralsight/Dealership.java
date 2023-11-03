package com.pluralsight;

import java.util.List;
import java.util.ArrayList;

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
        List<Vehicle> found = new ArrayList<>(inventory);
        if (min != -1) {found.removeIf(vehicle -> vehicle.getPrice()<min);}
        if (max != -1) {found.removeIf(vehicle -> vehicle.getPrice()>max);}

        if(found.isEmpty()){
            found.add(new Vehicle(-1,-1,"ERROR", "NONE FOUND", "NONE", "NONE", -1, -1));
        }

        found.sort(Vehicle.comparePrice());
        return found;
    }
    public List<Vehicle> getVehiclesByMakeModel(String make, String model){
        List<Vehicle> found = new ArrayList<>(inventory);
        found.removeIf(vehicle -> !vehicle.getMake().equalsIgnoreCase(make));
        found.removeIf(vehicle -> !vehicle.getModel().equalsIgnoreCase(model));

        if(found.isEmpty()){
            found.add(new Vehicle(-1,-1,"ERROR", "NONE FOUND", "NONE", "NONE", -1, -1));
        }

        found.sort(Vehicle.compareMakeModel());
        return found;
    }
    public List<Vehicle> getVehiclesByYear(int min, int max){
        List<Vehicle> found = new ArrayList<>(inventory);
        found.removeIf(vehicle -> vehicle.getPrice()<min);
        found.removeIf(vehicle -> vehicle.getPrice()>max);

        if(found.isEmpty()){
            found.add(new Vehicle(-1,-1,"ERROR", "NONE FOUND", "NONE", "NONE", -1, -1));
        }

        found.sort(Vehicle.compareYear());
        return found;
    }
    public List<Vehicle> getVehiclesByColor(String color){
        List<Vehicle> found = new ArrayList<>(inventory);
        found.removeIf(vehicle -> !vehicle.getColor().equalsIgnoreCase(color));

        if(found.isEmpty()){
            found.add(new Vehicle(-1,-1,"ERROR", "NONE FOUND", "NONE", "NONE", -1, -1));
        }

        found.sort(Vehicle.compareColor());
        return found;
    }
    public List<Vehicle> getVehiclesByMileage(int min, int max){
        List<Vehicle> found = new ArrayList<>(inventory);
        found.removeIf(vehicle -> vehicle.getOdometer()<min);
        found.removeIf(vehicle -> vehicle.getOdometer()>max);

        if(found.isEmpty()){
            found.add(new Vehicle(-1,-1,"ERROR", "NONE FOUND", "NONE", "NONE", -1, -1));
        }

        found.sort(Vehicle.compareMileage());
        return found;
    }
    public List<Vehicle> getVehiclesByType(String vehicleType){
        List<Vehicle> found = new ArrayList<>(inventory);
        found.removeIf(vehicle -> !vehicle.getVehicleType().equalsIgnoreCase(vehicleType));

        if(found.isEmpty()){
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
