package com.pluralsight;

import java.io.*;

public class DealershipFileManager {
    /*---------------VARIABLES---------------*/

    //CODE HERE

    /*--------------CONSTRUCTORS-------------*/

    //CODE HERE

    /*------------GETTERS/SETTERS------------*/

    //CODE HERE

    /*---------------FUNCTIONS---------------*/

    public Dealership getDealership(){
        Dealership dealership = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("inventory.csv"));
            String input = bufferedReader.readLine();
            String[] dealershipInfo = input.split("\\|");
            dealership = new Dealership(dealershipInfo[0], dealershipInfo[1],dealershipInfo[2]);
            while ((input = bufferedReader.readLine()) != null){
                String[] tokens = input.split("\\|");
                int vin = Integer.parseInt(tokens[0]);
                int year = Integer.parseInt(tokens[1]);
                String make = tokens[2];
                String model = tokens[3];
                String vehicleType = tokens[4];
                String color = tokens[5];
                int odometer = Integer.parseInt(tokens[6]);
                double price = Double.parseDouble(tokens[7]);
                dealership.addVehicle(new Vehicle(vin, year, make, model, vehicleType, color, odometer, price));
            }
            bufferedReader.close();
        }
        catch (IOException e){
            System.out.println("ERROR: COULD NOT CREATE FILE READER");
            e.printStackTrace();
        }
        catch (Exception e){
            System.out.println("AN UNSPECIFIED ERROR HAS OCCURRED");
            e.printStackTrace();
        }

        return dealership;
    }

    public void saveDealership(Dealership dealership){
        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("inventory.csv"));
            String output = String.format("%s|%s|%s", dealership.getName(), dealership.getAddress(), dealership.getPhone());
            bufferedWriter.write(output);
            for(Vehicle vehicle:dealership.getAllVehicles()){
                output=String.format("%s|%s|%s|%s|%s|%s|%s|%s%n", vehicle.getVin(), vehicle.getYear(), vehicle.getMake(), vehicle.getModel(), vehicle.getVehicleType(), vehicle.getColor(), vehicle.getOdometer(), vehicle.getPrice());
                bufferedWriter.write(output);
            }
            bufferedWriter.close();
        }
        catch (Exception e){
            System.out.println("ERROR: Could not save Inventory!");
            e.printStackTrace();
        }
    }
}
