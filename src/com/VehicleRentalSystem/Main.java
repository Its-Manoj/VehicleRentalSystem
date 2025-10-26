package com.VehicleRentalSystem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RentalService service = new RentalService();
        Scanner sc = new Scanner(System.in);

        System.out.println("🚗🚗🚗🚗🚗🚗 Welcome to Vehicle Rental System 🚗🚗🚗🚗🚗🚗");

        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        String customerId = service.generateNextCustomerId();
        Customer customer = new Customer(customerId, name);
        service.addCustomerIfNotExists(customer);

        while (true) {
            System.out.println("\n==============================");
            System.out.println("1. Show Available Vehicles");
            System.out.println("2. Rent a Vehicle");
            System.out.println("3. View Rental History");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    service.showVehiclesFromDB();
                    break;
                case 2:
                    System.out.print("Enter Vehicle ID to rent: ");
                    String vehicleId = sc.next();
                    System.out.print("Enter number of rental days: ");
                    int days = sc.nextInt();

                    Vehicle vehicle = service.getVehicleByIdFromDB(vehicleId);
                    if (vehicle != null) {
                        service.rentVehicleToDB(customer, vehicle, days);
                    } else {
                        System.out.println("❌ Vehicle ID not found in database.");
                    }
                    break;
                case 3:
                    service.showRentalHistoryFromDB();
                    break;
                case 4:
                    System.out.println("🙏 Thank you for using Vehicle Rental System!");
                    sc.close();
                    return;
                default:
                    System.out.println("⚠️ Invalid choice. Please try again.");
            }
        }
    }
}