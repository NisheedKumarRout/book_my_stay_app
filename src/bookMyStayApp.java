import java.util.HashMap;


class RoomInventory {

    private HashMap<String, Integer> inventory;

    public RoomInventory() {

        inventory = new HashMap<>();

        // Initial room availability
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    public int getAvailability(String roomType) {

        return inventory.getOrDefault(roomType, 0);
    }

    public void updateAvailability(String roomType, int count) {

        inventory.put(roomType, count);
    }

    public void displayInventory() {

        System.out.println("\nCurrent Room Inventory:");

        for (String roomType : inventory.keySet()) {

            System.out.println(roomType + " : " + inventory.get(roomType));
        }
    }
}

public class bookMyStayApp {

    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println("Book My Stay App - Version 3.0");
        System.out.println("Centralized Room Inventory");
        System.out.println("=====================================");

        RoomInventory inventory = new RoomInventory();

        inventory.displayInventory();

        System.out.println("\nChecking availability for Double Room:");
        System.out.println("Available: " +
                inventory.getAvailability("Double Room"));

        System.out.println("\nUpdating Suite Room availability...");
        inventory.updateAvailability("Suite Room", 4);

        inventory.displayInventory();
    }
}