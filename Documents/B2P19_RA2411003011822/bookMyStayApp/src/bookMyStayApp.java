import java.util.*;

class BookingRequest {
    private String guestName;
    private String roomType;

    public BookingRequest(String guestName, String roomType) {
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

class Inventory {
    private Map<String, Integer> rooms = new HashMap<>();

    public Inventory() {
        rooms.put("Standard", 2);
        rooms.put("Deluxe", 1);
        rooms.put("Suite", 1);
    }

    public synchronized boolean allocateRoom(String roomType) {
        int count = rooms.getOrDefault(roomType, 0);
        if (count > 0) {
            rooms.put(roomType, count - 1);
            return true;
        }
        return false;
    }

    public synchronized void displayInventory() {
        System.out.println("Current Inventory: " + rooms);
    }
}

class BookingProcessor implements Runnable {
    private Queue<BookingRequest> queue;
    private Inventory inventory;

    public BookingProcessor(Queue<BookingRequest> queue, Inventory inventory) {
        this.queue = queue;
        this.inventory = inventory;
    }

    public void run() {
        while (true) {
            BookingRequest request;

            synchronized (queue) {
                if (queue.isEmpty()) {
                    return;
                }
                request = queue.poll();
            }

            boolean success = inventory.allocateRoom(request.getRoomType());

            if (success) {
                System.out.println("Booking successful for " + request.getGuestName() + " (" + request.getRoomType() + ")");
            } else {
                System.out.println("Booking failed for " + request.getGuestName() + " (" + request.getRoomType() + ")");
            }
        }
    }
}

public class UseCase11ConcurrentBookingSimulation {
    public static void main(String[] args) {

        Queue<BookingRequest> queue = new LinkedList<>();
        Inventory inventory = new Inventory();

        queue.add(new BookingRequest("Alice", "Standard"));
        queue.add(new BookingRequest("Bob", "Standard"));
        queue.add(new BookingRequest("Charlie", "Standard"));
        queue.add(new BookingRequest("David", "Deluxe"));
        queue.add(new BookingRequest("Eve", "Suite"));

        Thread t1 = new Thread(new BookingProcessor(queue, inventory));
        Thread t2 = new Thread(new BookingProcessor(queue, inventory));
        Thread t3 = new Thread(new BookingProcessor(queue, inventory));

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        inventory.displayInventory();
    }
}