import java.util.*;



class Reservation {

    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
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


/* Inventory Service */

class RoomInventory {

    private HashMap<String, Integer> inventory;

    public RoomInventory() {

        inventory = new HashMap<>();

        inventory.put("Single Room", 2);
        inventory.put("Double Room", 2);
        inventory.put("Suite Room", 1);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void decrementRoom(String roomType) {

        int count = inventory.get(roomType);

        if (count > 0) {
            inventory.put(roomType, count - 1);
        }
    }
}


/* Booking Service */

class BookingService {

    private Queue<Reservation> bookingQueue;
    private RoomInventory inventory;

    /* Track allocated rooms */
    private HashMap<String, Set<String>> allocatedRooms;

    public BookingService(RoomInventory inventory) {

        this.inventory = inventory;

        bookingQueue = new LinkedList<>();

        allocatedRooms = new HashMap<>();

        allocatedRooms.put("Single Room", new HashSet<>());
        allocatedRooms.put("Double Room", new HashSet<>());
        allocatedRooms.put("Suite Room", new HashSet<>());
    }

    /* Add booking request */

    public void addReservation(Reservation reservation) {

        bookingQueue.add(reservation);

        System.out.println("Booking request received from " + reservation.getGuestName());
    }


    /* Process queue */

    public void processBookings() {

        System.out.println("\nProcessing booking requests...\n");

        while (!bookingQueue.isEmpty()) {

            Reservation request = bookingQueue.poll();

            String roomType = request.getRoomType();

            if (inventory.getAvailability(roomType) > 0) {

                String roomID = generateRoomID(roomType);

                allocatedRooms.get(roomType).add(roomID);

                inventory.decrementRoom(roomType);

                System.out.println("Reservation Confirmed");
                System.out.println("Guest: " + request.getGuestName());
                System.out.println("Room Type: " + roomType);
                System.out.println("Assigned Room ID: " + roomID);
                System.out.println("-----------------------------");

            } else {

                System.out.println("Reservation Failed for "
                        + request.getGuestName()
                        + " (No " + roomType + " available)");
            }
        }
    }


    /* Generate unique room ID */

    private String generateRoomID(String roomType) {

        Set<String> roomSet = allocatedRooms.get(roomType);

        int id = roomSet.size() + 1;

        return roomType.substring(0, 2).toUpperCase() + id;
    }
}


/* Main Application */


    public void displayReservation() {
        System.out.println("Guest: " + guestName + " | Requested Room: " + roomType);
    }
}


/* Booking Queue Manager */

class BookingRequestQueue {

    private Queue<Reservation> bookingQueue;

    public BookingRequestQueue() {
        bookingQueue = new LinkedList<>();
    }

    /* Add booking request */

    public void addRequest(Reservation reservation) {

        bookingQueue.add(reservation);
        System.out.println("Booking request added for " + reservation.getGuestName());
    }

    /* Display all requests in queue */

    public void displayQueue() {

        System.out.println("\nCurrent Booking Request Queue:");

        for (Reservation r : bookingQueue) {
            r.displayReservation();
        }
    }
}


/* Main Application */
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

        System.out.println("==========================================");
        System.out.println("Book My Stay App - Version 6.0");
        System.out.println("Reservation Confirmation & Allocation");
        System.out.println("==========================================");

        RoomInventory inventory = new RoomInventory();

        BookingService bookingService = new BookingService(inventory);

        /* Booking requests */

        bookingService.addReservation(new Reservation("Alice", "Single Room"));
        bookingService.addReservation(new Reservation("Bob", "Double Room"));
        bookingService.addReservation(new Reservation("Charlie", "Suite Room"));
        bookingService.addReservation(new Reservation("David", "Suite Room"));

        bookingService.processBookings();
=======
        System.out.println("======================================");
        System.out.println("Book My Stay App - Version 5.0");
        System.out.println("Booking Request Queue (FIFO)");
        System.out.println("======================================");

        BookingRequestQueue requestQueue = new BookingRequestQueue();

        /* Guest booking requests */

        Reservation r1 = new Reservation("Alice", "Single Room");
        Reservation r2 = new Reservation("Bob", "Double Room");
        Reservation r3 = new Reservation("Charlie", "Suite Room");

        requestQueue.addRequest(r1);
        requestQueue.addRequest(r2);
        requestQueue.addRequest(r3);

        requestQueue.displayQueue();
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