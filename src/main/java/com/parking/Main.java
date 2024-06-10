public class Main {
    public static void main(String[] args) {
        CostStrategy costStrategy = new CostStrategy();
        ParkingLot parkingLot = new ParkingLot(1, 2, costStrategy); // 1 floor, 2 spaces per floor

        // Initialize parking lot
        System.out.println("Initializing Parking Lot...");

        // Add Vehicles
        Vehicle car1 = new Vehicle("Car", "ABC123", "Red");
        Vehicle car2 = new Vehicle("Car", "XYZ789", "Blue");

        long currentTime = System.currentTimeMillis() / 1000L; // current time in seconds
        String token1 = parkingLot.addVehicle(car1, currentTime);
        String token2 = parkingLot.addVehicle(car2, currentTime + 3600); // 1 hour later

        System.out.println("Tokens issued: " + token1 + ", " + token2);

        // Check availability
        System.out.println("Is there any space available for Car on floor 1: " + parkingLot.checkAvailability("Car", 1));

        // Remove Vehicle
        long exitTime = currentTime + 7200; // 2 hours later
        parkingLot.removeVehicle(token1, exitTime);

        // Try to add another vehicle
        Vehicle car3 = new Vehicle("Car", "LMN456", "Black");
        try {
            String token3 = parkingLot.addVehicle(car3, currentTime + 10800); // 3 hours later
            System.out.println("Token issued for the third car: " + token3);
        } catch (RuntimeException e) {
            System.out.println("Failed to add third car: " + e.getMessage());
        }
    }
}