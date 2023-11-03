package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;

public class UserInterface {
    /*---------------VARIABLES---------------*/

    private Dealership dealership;
    List<Vehicle> searched;
    Scanner scanner = new Scanner(System.in);
    String input;
    boolean validSearch;

    /*--------------CONSTRUCTORS-------------*/

    //CODE HERE

    /*------------GETTERS/SETTERS------------*/

    //CODE HERE

    /*---------------FUNCTIONS---------------*/

    private void init(){
        DealershipFileManager dealershipFileManager = new DealershipFileManager();
        dealership = dealershipFileManager.getDealership();
    }

    public void display(){
        init();
        int command;
        boolean running = true;
        do {
            System.out.println("Welcome to the CallMeDealership Inventory Management Suite!");
            System.out.println("Your options are: ");
            System.out.println("""
                    1 - Find vehicles within a price range
                    2 - Find vehicles by make / model
                    3 - Find vehicles by year range
                    4 - Find vehicles by color
                    5 - Find vehicles by mileage range
                    6 - Find vehicles by type (car, truck, SUV, van)
                    7 - List ALL vehicles
                    8 - Add a vehicle
                    9 - Remove a vehicle
                    99 - Quit""");
            System.out.print("Which would you like to do? ");
            input = scanner.nextLine();
            try {
                command = Integer.parseInt(input);
            }
            catch (Exception e){
                command = -1;
            }

            switch (command) {
                case 1 -> processGetByPriceRequest();
                case 2 -> processGetByMakeModelRequest();
                case 3 -> processGetByYearRequest();
                case 4 -> processGetByColorRequest();
                case 5 -> processGetByMileageRequest();
                case 6 -> processGetByVehicleTypeRequest();
                case 7 -> processAllVehiclesRequest();
                case 8 -> processAddVehicleRequest();
                case 9 -> processRemoveVehicleRequest();
                case 99 -> {
                    System.out.println("Thank you for using the CallMeDealership Inventory Management Suite! Goodbye!");
                    running = false;
                }
                default -> System.out.println("ERROR: INVALID CHOICE");
            }
        }while(running);
    }

    private void displayVehicles(List<Vehicle> inventory){
        System.out.println();
        System.out.println("""
                            +-----+----+----------+-----------+------+------+------+----------+
                            | VIN |YEAR|   MAKE   |   MODEL   | TYPE |COLOR |MILES |  PRICE   |
                            +-----+----+----------+-----------+------+------+------+----------+""");
        for(Vehicle vehicle:inventory){
            //negative sign left justifies, first number sets padding, second number sets max width
            String formattedVIN = String.format("%-5d", vehicle.getVin());
            String formattedYear = String.format("%-4d", vehicle.getYear());
            String formattedMake = String.format("%-10.10s", vehicle.getMake());
            String formattedModel = String.format("%-11.11s", vehicle.getModel());
            String formattedType = String.format("%-6s",vehicle.getVehicleType());
            String formattedColor = String.format("%-6s", vehicle.getColor());
            String formattedOdometer = String.format("%-6d", vehicle.getOdometer());
            String formattedPrice = String.format("%-6.2f", vehicle.getPrice()); //Max of 6 numbers before decimal, 2 after
            formattedPrice = String.format("$%9.9s", formattedPrice); //Done twice to add needed padding to value after converting from double
            String output = String.format("|%s|%s|%s|%s|%s|%s|%s|%s|", formattedVIN, formattedYear, formattedMake, formattedModel,
                    formattedType, formattedColor, formattedOdometer, formattedPrice);
            System.out.println(output);
            System.out.println("+-----+----+----------+-----------+------+------+------+----------+");
        }
    }

    private void processAllVehiclesRequest(){
        List<Vehicle> vehicles = new ArrayList<>(dealership.getAllVehicles());
        vehicles.sort(Vehicle.compareVIN());
        displayVehicles(vehicles);
    }

    private void processGetByPriceRequest(){
        System.out.print("Please type a lower bound for the price (Type -1 to ignore): $");
        double minPrice = scanner.nextDouble();
        System.out.print("Please type an upper bound for the price (Type -1 to ignore): $");
        double maxPrice = scanner.nextDouble();
        scanner.nextLine(); //Eat leftover newline to not cause problems
        searched = dealership.getVehiclesByPrice(minPrice, maxPrice);
        displayVehicles(searched);
    }
    private void processGetByMakeModelRequest(){
        System.out.println("Please type the make you would like to search for: ");
        String make = scanner.nextLine();
        System.out.println("Please type the model you would like to search for: ");
        String model = scanner.nextLine();
        searched = dealership.getVehiclesByMakeModel(make, model);
        displayVehicles(searched);
    }
    private void processGetByYearRequest(){
        System.out.print("Please type a lower bound for the year: ");
        int minYear = scanner.nextInt();
        System.out.print("Please type an upper bound for the year: ");
        int maxYear = scanner.nextInt();
        scanner.nextLine(); //Eat leftover newline to not cause problems
        searched = dealership.getVehiclesByYear(minYear, maxYear);
        displayVehicles(searched);
    }
    private void processGetByColorRequest(){
        //This search is much more complex than the others because I originally overcomplicated things, and Raymond advised me as such after this was completed.
        String color = "";
        do {
            validSearch = false;
            System.out.print("Please type the color you would like to search for: ");
            input = scanner.nextLine();

            if(input.isBlank()){
                System.out.println("ERROR: You need to supply a color to search for!");
            }
            else if (!input.matches("[a-zA-Z]+")) {
                System.out.println("ERROR: Search must only contain letters!");
            }
            else{
                color=input;
                validSearch = true;
            }
        } while (!validSearch);
        searched = dealership.getVehiclesByColor(color);
        displayVehicles(searched);
    }
    private void processGetByMileageRequest(){
        //This search is much more complex than the others because I originally overcomplicated things, and Raymond advised me as such after this was completed.
        try {
            int minMileage = 0;
            int maxMileage = 0;
            validSearch = false;
            do {
                System.out.print("Please type a lower bound for the mileage (Type -1 to ignore): ");
                input = scanner.nextLine();
                if (input.isEmpty() || input.matches("\\D+")) { //Input must contain only numbers
                    System.out.println("ERROR: Term must be a number! Ignoring input...");
                } else {
                    minMileage = Integer.parseInt(input);
                    validSearch = true; //Allows loop to end
                }

                System.out.print("Please type an upper bound for the mileage (Type -1 to ignore): ");
                input = scanner.nextLine();
                if (input.isEmpty() || input.matches("\\D+")) { //Input must contain only numbers
                    System.out.println("ERROR: Term must be a number! Ignoring input...");
                } else {
                    maxMileage = Integer.parseInt(input);
                    validSearch = true; //Allows loop to end
                }

                if(maxMileage == -1 && minMileage == -1){ //Error if both terms are ignored
                    System.out.println("ERROR: You need to supply at least one search term!");
                    validSearch = false; //Resets loop checker
                }
            } while (!validSearch);
            searched = dealership.getVehiclesByMileage(minMileage, maxMileage);
            displayVehicles(searched);
        }
        catch (NumberFormatException e){
            System.out.println("ERROR: Could not parse input!");
            e.printStackTrace();
        }
        catch (Exception e){
            System.out.println("ERROR: Unspecified issue with search! Check formatting of inputs!");
            e.printStackTrace();
        }
    }
    private void processGetByVehicleTypeRequest(){
        System.out.print("Please type the type of vehicle you would like to search for: ");
        String type = scanner.nextLine();
        searched = dealership.getVehiclesByType(type);
        displayVehicles(searched);
    }
    private void processAddVehicleRequest(){

        System.out.print("Please type the VIN of the new vehicle: ");
        int vin = scanner.nextInt();
        System.out.print("Please type the year of the new vehicle:");
        int year = scanner.nextInt();
        scanner.nextLine(); //Eat leftover newline to not cause problems
        System.out.print("Please type the make of the new vehicle:");
        String make = scanner.nextLine();
        System.out.print("Please type the model of the new vehicle:");
        String model = scanner.nextLine();
        System.out.print("Please type the type of the new vehicle:");
        String type = scanner.nextLine();
        System.out.print("Please type the color of the new vehicle:");
        String color = scanner.nextLine();
        System.out.print("Please type the mileage of the new vehicle:");
        int odometer = scanner.nextInt();
        System.out.print("Please type the price of the new vehicle:");
        double price = scanner.nextDouble();
        scanner.nextLine(); //Eat leftover newline to not cause problems

        Vehicle vehicle = new Vehicle(vin, year, make, model, type, color,odometer, price);
        dealership.addVehicle(vehicle);
        System.out.println("Vehicle added!");
        DealershipFileManager dealershipFileManager = new DealershipFileManager();
        dealershipFileManager.saveDealership(dealership);
    }
    private void processRemoveVehicleRequest(){
        try{
            System.out.print("Please type the VIN of the vehicle to remove: ");
            int vin = scanner.nextInt();
            scanner.nextLine(); //Eat leftover newline to not cause problems
            for(Vehicle vehicle : dealership.getAllVehicles()){
                if(vehicle.getVin() == vin){
                    dealership.removeVehicle(vehicle);
                    System.out.println("Vehicle removed!");
                }
            }
        }
        catch (Exception e){
            System.out.println("ERROR: Could not remove from Inventory!");
            e.printStackTrace();
        }

        DealershipFileManager dealershipFileManager = new DealershipFileManager();
        dealershipFileManager.saveDealership(dealership);
    }

}
