import java.io.*;
import java.util.*;

class Reservation implements Serializable {
    private String reservationId;
    private String guestName;
    private String roomType;

    public Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String toString() {
        return reservationId + " " + guestName + " " + roomType;
    }
}

class SystemState implements Serializable {
    List<Reservation> reservations;
    Map<String, Integer> inventory;

    public SystemState(List<Reservation> reservations, Map<String, Integer> inventory) {
        this.reservations = reservations;
        this.inventory = inventory;
    }
}

class PersistenceService {
    private static final String FILE_NAME = "system_state.ser";

    public static void save(SystemState state) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
            out.writeObject(state);
            out.close();
            System.out.println("State saved successfully");
        } catch (Exception e) {
            System.out.println("Error saving state");
        }
    }

    public static SystemState load() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME));
            SystemState state = (SystemState) in.readObject();
            in.close();
            System.out.println("State loaded successfully");
            return state;
        } catch (Exception e) {
            System.out.println("No previous state found, starting fresh");
            return null;
        }
    }
}

public class UseCase12DataPersistenceRecovery {
    public static void main(String[] args) {

        SystemState state = PersistenceService.load();

        List<Reservation> reservations;
        Map<String, Integer> inventory;

        if (state == null) {
            reservations = new ArrayList<>();
            inventory = new HashMap<>();

            inventory.put("Standard", 2);
            inventory.put("Deluxe", 1);
            inventory.put("Suite", 1);

            reservations.add(new Reservation("R401", "Alice", "Standard"));
            reservations.add(new Reservation("R402", "Bob", "Deluxe"));

        } else {
            reservations = state.reservations;
            inventory = state.inventory;
        }

        for (Reservation r : reservations) {
            System.out.println(r);
        }

        System.out.println("Inventory: " + inventory);

        PersistenceService.save(new SystemState(reservations, inventory));
    }
}