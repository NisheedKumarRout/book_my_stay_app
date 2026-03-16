import java.util.HashMap;




abstract class Room {

    protected String roomType;
    protected double price;

    public Room(String roomType, double price) {
        this.roomType = roomType;
        this.price = price;
    }

    public void displayDetails() {
        System.out.println("Room Type: " + roomType);
        System.out.println("Price per night: $" + price);
    }

    public String getRoomType() {
        return roomType;
    }
}

class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 100);
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 180);
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 350);
    }
}


/* Inventory Management (from UC3) */


class RoomInventory {

    private HashMap<String, Integer> inventory;

    public RoomInventory() {

        inventory = new HashMap<>();

        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 0);   // Example unavailable room
        // Initial room availability
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    public int getAvailability(String roomType) {

        return inventory.getOrDefault(roomType, 0);
    }

    public HashMap<String, Integer> getInventory() {

        return inventory;
    }
}


/* Search Service (Read-only operations) */

class RoomSearchService {

    private RoomInventory inventory;

    public RoomSearchService(RoomInventory inventory) {

        this.inventory = inventory;
    }

    public void searchAvailableRooms(Room[] rooms) {

        System.out.println("\nAvailable Rooms:");

        for (Room room : rooms) {

            int available = inventory.getAvailability(room.getRoomType());

            if (available > 0) {

                room.displayDetails();
                System.out.println("Available: " + available);
                System.out.println("--------------------------");
            }
        }
    }
}


=======
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
        System.out.println("Book My Stay App - Version 4.0");
        System.out.println("Room Search & Availability Check");

        System.out.println("Book My Stay App - Version 3.0");
        System.out.println("Centralized Room Inventory");
        System.out.println("=====================================");

        RoomInventory inventory = new RoomInventory();

        Room[] rooms = {
                new SingleRoom(),
                new DoubleRoom(),
                new SuiteRoom()
        };

        RoomSearchService searchService = new RoomSearchService(inventory);

        searchService.searchAvailableRooms(rooms);

        inventory.displayInventory();

        System.out.println("\nChecking availability for Double Room:");
        System.out.println("Available: " +
                inventory.getAvailability("Double Room"));

        System.out.println("\nUpdating Suite Room availability...");
        inventory.updateAvailability("Suite Room", 4);

        inventory.displayInventory();
    }
}