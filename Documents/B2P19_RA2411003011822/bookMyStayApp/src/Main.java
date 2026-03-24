import java.util.*;

class AddOnService {
    private String serviceName;
    private double cost;

    public AddOnService(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getCost() {
        return cost;
    }
}

class AddOnServiceManager {
    private Map<String, List<AddOnService>> serviceMap = new HashMap<>();

    public void addService(String reservationId, AddOnService service) {
        serviceMap.putIfAbsent(reservationId, new ArrayList<>());
        serviceMap.get(reservationId).add(service);
    }

    public List<AddOnService> getServices(String reservationId) {
        return serviceMap.getOrDefault(reservationId, new ArrayList<>());
    }

    public double calculateTotalCost(String reservationId) {
        double total = 0;
        List<AddOnService> services = getServices(reservationId);
        for (AddOnService service : services) {
            total += service.getCost();
        }
        return total;
    }
}

public class Main {
    public static void main(String[] args) {
        AddOnServiceManager manager = new AddOnServiceManager();

        String reservationId = "R101";

        manager.addService(reservationId, new AddOnService("Breakfast", 500));
        manager.addService(reservationId, new AddOnService("Airport Pickup", 1000));
        manager.addService(reservationId, new AddOnService("Extra Bed", 800));

        List<AddOnService> services = manager.getServices(reservationId);

        for (AddOnService s : services) {
            System.out.println(s.getServiceName() + " " + s.getCost());
        }

        System.out.println(manager.calculateTotalCost(reservationId));
    }
}