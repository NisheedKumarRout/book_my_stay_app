import java.util.*;

class Reservation {
    private String reservationId;
    private String guestName;
    private String roomType;
    private double price;

    public Reservation(String reservationId, String guestName, String roomType, double price) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
        this.price = price;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public double getPrice() {
        return price;
    }
}

class BookingHistory {
    private List<Reservation> reservations = new ArrayList<>();

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public List<Reservation> getAllReservations() {
        return reservations;
    }
}

class BookingReportService {
    public void displayAllBookings(List<Reservation> reservations) {
        for (Reservation r : reservations) {
            System.out.println(r.getReservationId() + " " + r.getGuestName() + " " + r.getRoomType() + " " + r.getPrice());
        }
    }

    public void generateSummary(List<Reservation> reservations) {
        int totalBookings = reservations.size();
        double totalRevenue = 0;

        for (Reservation r : reservations) {
            totalRevenue += r.getPrice();
        }

        System.out.println("Total Bookings: " + totalBookings);
        System.out.println("Total Revenue: " + totalRevenue);
    }
}

public class bookMyStayApp {
    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();
        BookingReportService reportService = new BookingReportService();

        history.addReservation(new Reservation("R101", "Alice", "Deluxe", 2000));
        history.addReservation(new Reservation("R102", "Bob", "Suite", 3500));
        history.addReservation(new Reservation("R103", "Charlie", "Standard", 1500));

        List<Reservation> allReservations = history.getAllReservations();

        reportService.displayAllBookings(allReservations);
        reportService.generateSummary(allReservations);
    }
}