import java.util.*;

class Reservation {
    private String reservationId;
    private String roomType;
    private String roomId;

    public Reservation(String reservationId, String roomType, String roomId) {
        this.reservationId = reservationId;
        this.roomType = roomType;
        this.roomId = roomId;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getRoomId() {
        return roomId;
    }
}

class Inventory {
    private Map<String, Integer> roomCount = new HashMap<>();

    public Inventory() {
        roomCount.put("Standard", 2);
        roomCount.put("Deluxe", 1);
        roomCount.put("Suite", 1);
    }

    public void incrementRoom(String roomType) {
        roomCount.put(roomType, roomCount.getOrDefault(roomType, 0) + 1);
    }

    public int getCount(String roomType) {
        return roomCount.getOrDefault(roomType, 0);
    }
}

class CancellationService {
    private Map<String, Reservation> reservations;
    private Stack<String> rollbackStack;
    private Inventory inventory;

    public CancellationService(Map<String, Reservation> reservations, Stack<String> rollbackStack, Inventory inventory) {
        this.reservations = reservations;
        this.rollbackStack = rollbackStack;
        this.inventory = inventory;
    }

    public void cancelReservation(String reservationId) {
        if (!reservations.containsKey(reservationId)) {
            System.out.println("Cancellation failed: Reservation not found");
            return;
        }

        Reservation r = reservations.get(reservationId);

        rollbackStack.push(r.getRoomId());
        inventory.incrementRoom(r.getRoomType());
        reservations.remove(reservationId);

        System.out.println("Cancellation successful for: " + reservationId);
    }
}

public class bookMyStayApp {
    public static void main(String[] args) {

        Map<String, Reservation> reservations = new HashMap<>();
        Stack<String> rollbackStack = new Stack<>();
        Inventory inventory = new Inventory();

        reservations.put("R301", new Reservation("R301", "Deluxe", "D1"));
        reservations.put("R302", new Reservation("R302", "Suite", "S1"));

        CancellationService service = new CancellationService(reservations, rollbackStack, inventory);

        service.cancelReservation("R301");
        service.cancelReservation("R999");

        System.out.println("Available Deluxe Rooms: " + inventory.getCount("Deluxe"));
        System.out.println("Rollback Stack: " + rollbackStack);
    }
}