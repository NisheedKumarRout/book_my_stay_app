import java.util.Queue;
import java.util.LinkedList;


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

public class bookMyStayApp {

    public static void main(String[] args) {

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
    }
}