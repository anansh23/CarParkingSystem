import java.util.HashMap;
import java.util.Map;

class CostStrategy {
    private Map<String, Integer> costPerHour;

    public CostStrategy() {
        costPerHour = new HashMap<>();
        costPerHour.put("Bike", 10);
        costPerHour.put("Car", 20);
        costPerHour.put("Truck", 30);
        costPerHour.put("Bus", 30);
    }

    public int calculateCost(String vehicleType, long duration) {
        return costPerHour.get(vehicleType) * (int) duration;
    }
}