import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ParkingLot {
    private List<Floor> floors;
    private Map<String, Long> vehicleEntryTime;
    private CostStrategy costStrategy;
    private int tokenCounter;

    public ParkingLot(int numOfFloors, int capacityPerFloor, CostStrategy costStrategy) {
        this.floors = new ArrayList<>();
        for (int i = 0; i < numOfFloors; i++) {
            floors.add(new Floor(i + 1, capacityPerFloor));
        }
        this.vehicleEntryTime = new HashMap<>();
        this.costStrategy = costStrategy;
        this.tokenCounter = 0;
    }

    public String addVehicle(Vehicle vehicle, long timestamp) {
        for (Floor floor : floors) {
            if (floor.hasAvailableSpace()) {
                floor.parkVehicle(vehicle);
                String token = generateToken();
                vehicleEntryTime.put(token, timestamp);
                return token;
            }
        }
        throw new RuntimeException("Parking Lot is Full");
    }

    public Vehicle removeVehicle(String token, long timestamp) {
        for (Floor floor : floors) {
            for (VehicleSpace space : floor.getSpaces()) {
                Vehicle vehicle = space.getVehicle();
                if (vehicle != null && vehicleEntryTime.containsKey(token)) {
                    long entryTime = vehicleEntryTime.remove(token);
                    long parkedDuration = (timestamp - entryTime) / 3600; // duration in hours
                    System.out.println("Vehicle " + vehicle.getRegistrationNumber() + " parked for " + parkedDuration + " hours. Total cost: " + costStrategy.calculateCost(vehicle.getType(), parkedDuration));
                    space.removeVehicle();
                    return vehicle;
                }
            }
        }
        throw new RuntimeException("Vehicle not found for the given token");
    }

    public boolean checkAvailability(String vehicleType, int floorNumber) {
        if (floorNumber <= floors.size() && floorNumber > 0) {
            return floors.get(floorNumber - 1).hasAvailableSpace();
        }
        return false;
    }

    private String generateToken() {
        return "TOKEN" + (++tokenCounter);
    }
}