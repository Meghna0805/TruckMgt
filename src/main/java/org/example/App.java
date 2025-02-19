package org.example;

import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        TruckService truckService = new TruckService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("🚚🚛 Welcome to the Truck Management System 🚛🚚\n");

        // Step 1: Adding multiple trucks to the database
        System.out.println("🔹 Adding trucks to the database...");
        Truck[] trucks = {
                new Truck("TATA", "2019", 1000, "Rajesh"),
                new Truck("VOLVO", "2023", 1200, "Rakesh"),
                new Truck("EICHER", "2022", 1500, "Harshit"),
                new Truck("ASHOK LEYLAND", "2020", 1800, "Vikas"),
                new Truck("BHARATBENZ", "2021", 1400, "Suresh"),
                new Truck("SCANIA", "2024", 1600, "Dinesh"),
                new Truck("MAN", "2023", 2000, "Mahesh"),
                new Truck("MAHINDRA", "2022", 1100, "Amit")
        };

        for (Truck truck : trucks) {
            truckService.addTruck(truck);
        }
        System.out.println("✅ All trucks added successfully!\n");

        // Step 2: Fetch a truck by ID
        System.out.print("🔍 Enter a Truck ID to fetch details: ");
        int fetchId = scanner.nextInt();
        Truck truck = truckService.getTruckById(fetchId);
        if (truck.getId() != 0) {
            System.out.println("✅ Truck Details: " + truck + "\n");
        } else {
            System.out.println("❌ No truck found with ID: " + fetchId + "\n");
        }

        // Step 3: Updating a truck's driver name
        System.out.print("✍️ Enter Truck ID to update driver name: ");
        int updateId = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.print("📝 Enter new Driver Name: ");
        String newDriver = scanner.nextLine();

        Truck updateTruck = truckService.getTruckById(updateId);
        if (updateTruck.getId() != 0) {
            updateTruck.setDriver_name(newDriver);
            truckService.updateTruck(updateTruck);
            System.out.println("✅ Truck Updated: " + truckService.getTruckById(updateId) + "\n");
        } else {
            System.out.println("❌ No truck found with ID: " + updateId + "\n");
        }

        // Step 4: Fetch all trucks
        System.out.println("📜 Fetching all truck records from the database...");
        List<Truck> allTrucks = truckService.getAllTrucks();
        for (Truck t : allTrucks) {
            System.out.println("🚛 Truck: " + t);
        }
        System.out.println();

        // Step 5: Searching trucks by capacity
        System.out.print("🔍 Enter truck capacity to search: ");
        int capacity = scanner.nextInt();
        System.out.println("🔎 Trucks with capacity " + capacity + " kg:");
        for (Truck t : allTrucks) {
            if (t.getCapacity() == capacity) {
                System.out.println("✅ " + t);
            }
        }
        System.out.println();

        // Step 6: Deleting a truck record
        System.out.print("🗑 Enter Truck ID to delete: ");
        int deleteId = scanner.nextInt();
        truckService.deleteTruck(deleteId);
        System.out.println("✅ Truck with ID " + deleteId + " deleted successfully!\n");

        // Step 7: Final truck list after deletion
        System.out.println("📜 All trucks after deletion:");
        allTrucks = truckService.getAllTrucks();
        for (Truck t : allTrucks) {
            System.out.println("🚛 Truck: " + t);
        }

        System.out.println("\n🚀 Truck Management System operations completed! 🚀");
        scanner.close();
    }
}
