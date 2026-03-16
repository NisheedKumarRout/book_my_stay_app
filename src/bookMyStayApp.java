/**
 * ============================================================
 * MAIN CLASS - UseCase2RoomInitialization
 * ============================================================
 * Use Case 2: Basic Room Types & Static Availability
 *
 * This program demonstrates:
 * - Abstraction using an abstract Room class
 * - Inheritance through different room types
 * - Polymorphism using Room references
 * - Static availability variables
 *
 * @author Developer
 * @version 2.0
 */

abstract class Room {

    protected String roomType;
    protected int beds;
    protected double size;
    protected double price;

    public Room(String roomType, int beds, double size, double price) {
        this.roomType = roomType;
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    public void displayRoomDetails() {
        System.out.println("Room Type: " + roomType);
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sqm");
        System.out.println("Price per night: $" + price);
    }
}

/* Single Room */
class SingleRoom extends Room {

    public SingleRoom() {
        super("Single Room", 1, 20, 100);
    }
}

/* Double Room */
class DoubleRoom extends Room {

    public DoubleRoom() {
        super("Double Room", 2, 35, 180);
    }
}

/* Suite Room */
class SuiteRoom extends Room {

    public SuiteRoom() {
        super("Suite Room", 3, 60, 350);
    }
}

public class bookMyStayApp {

    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println("Book My Stay App - Version 2.0");
        System.out.println("Room Availability");
        System.out.println("=====================================");

        // Polymorphism
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        // Static availability
        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;

        System.out.println("\n--- Single Room ---");
        singleRoom.displayRoomDetails();
        System.out.println("Available Rooms: " + singleAvailable);

        System.out.println("\n--- Double Room ---");
        doubleRoom.displayRoomDetails();
        System.out.println("Available Rooms: " + doubleAvailable);

        System.out.println("\n--- Suite Room ---");
        suiteRoom.displayRoomDetails();
        System.out.println("Available Rooms: " + suiteAvailable);

        System.out.println("\nSystem execution completed.");
    }
}