import java.util.ArrayList;
import java.util.List;

class Floor {
    private int floorNumber;
    private List<VehicleSpace> spaces;

    public Floor(int floorNumber, int capacity) {
        this.floorNumber = floorNumber;
        this.spaces = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            spaces.add(new VehicleSpace(i + 1));
        }
    }

    public boolean hasAvailableSpace() {
        for (VehicleSpace space : spaces) {
            if (space.isAvailable()) {
                return true;
            }
        }
        return false;
    }

    public boolean parkVehicle(Vehicle vehicle) {
        for (VehicleSpace space : spaces) {
            if (space.isAvailable()) {
                space.parkVehicle(vehicle);
                return true;
            }
        }
        return false;
    }

    public Vehicle removeVehicle(String registrationNumber) {
        for (VehicleSpace space : spaces) {
            if (!space.isAvailable() && space.getVehicle().getRegistrationNumber().equals(registrationNumber)) {
                return space.removeVehicle();
            }
        }
        return null;
    }

    public List<VehicleSpace> getSpaces() {
        return spaces;
    }
}