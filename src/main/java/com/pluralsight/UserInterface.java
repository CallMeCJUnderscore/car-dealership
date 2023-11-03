package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    /*---------------VARIABLES---------------*/

    private Dealership dealership;

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
        Scanner scanner = new Scanner(System.in);
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
            command = scanner.nextInt();
            scanner.nextLine();
            switch (command) {
                case 1 -> {
                    System.out.print("Please type a lower bound for the price: $");
                    double minPrice = scanner.nextDouble();
                    System.out.print("Please type an upper bound for the price: $");
                    double maxPrice = scanner.nextDouble();
                    dealership.getVehiclesByPrice(minPrice, maxPrice);
                }
                case 2 -> {
                    System.out.println("Please type the make you would like to search for: ");
                    String make = scanner.nextLine();
                    System.out.println("Please type the model you would like to search for: ");
                    String model = scanner.nextLine();
                    dealership.getVehiclesByMakeModel(make, model);
                }
                case 3 -> {
                    System.out.print("Please type a lower bound for the year: ");
                    int minYear = scanner.nextInt();
                    System.out.print("Please type an upper bound for the year: ");
                    int maxYear = scanner.nextInt();
                    dealership.getVehiclesByYear(minYear, maxYear);
                }
                case 4 -> {
                    System.out.println("Please type the color you would like to search for: ");
                    String color = scanner.nextLine();
                    dealership.getVehiclesByColor(color);
                }
                case 5 -> {
                    System.out.print("Please type a lower bound for the mileage: ");
                    int minMileage = scanner.nextInt();
                    System.out.print("Please type an upper bound for the mileage: ");
                    int maxMileage = scanner.nextInt();
                    dealership.getVehiclesByMileage(minMileage, maxMileage);
                }
                case 6 -> {
                    System.out.println("Please type the type of vehicle you would like to search for: ");
                    String type = scanner.nextLine();
                    dealership.getVehiclesByType(type);
                }
                case 7 -> processAllVehiclesRequest();
                case 8 -> dealership.addVehicle(new Vehicle(0, 0, null, null, null, null, 0, 0));
                case 9 -> dealership.removeVehicle(new Vehicle(0, 0, null, null, null, null, 0, 0));
                case 99 -> {
                    System.out.println("Thank you for using the CallMeDealership Inventory Management Suite! Goodbye!");
                    running = false;
                }
                default -> System.out.println("ERROR: INVALID CHOICE");
            }
        }while(running);
    }

    private void displayVehicles(ArrayList<Vehicle> inventory){
        //ADD LATER inventory.sort();
        System.out.println("""
                            +-----+----+----------+-----------+------+------+----------+
                            | VIN |YEAR|   MAKE   |   MODEL   |COLOR |MILES |  PRICE   |
                            +-----+----+----------+-----------+------+------+----------+""");
        for(Vehicle vehicle:inventory){
            String formattedVIN = String.format("%-5d", vehicle.getVin());
            String formattedYear = String.format("%-4d", vehicle.getYear());
            String formattedMake = String.format("%-10.10s", vehicle.getMake());
            String formattedModel = String.format("%-11.11s", vehicle.getModel());
            String formattedColor = String.format("%-6s", vehicle.getColor());
            String formattedOdometer = String.format("%-6d", vehicle.getOdometer());
            String formattedPrice = String.format("%-6.2f", vehicle.getPrice());
            formattedPrice = String.format("$%9.9s", formattedPrice);
            String output = String.format("|%s|%s|%s|%s|%s|%s|%s|", formattedVIN, formattedYear, formattedMake, formattedModel, formattedColor, formattedOdometer, formattedPrice);
            System.out.println(output);
            System.out.println("+-----+----+----------+-----------+------+------+----------+");
        }
    }

    private void processAllVehiclesRequest(){
        displayVehicles(dealership.getAllVehicles());
    }
}
