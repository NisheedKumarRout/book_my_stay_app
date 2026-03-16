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
    }
}