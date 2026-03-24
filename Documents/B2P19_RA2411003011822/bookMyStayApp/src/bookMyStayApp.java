import java.util.*;

class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

class BookingValidator {
    private static final List<String> validRoomTypes = Arrays.asList("Standard", "Deluxe", "Suite");

    public static void validate(String roomType, int availableRooms) throws InvalidBookingException {
        if (!validRoomTypes.contains(roomType)) {
            throw new InvalidBookingException("Invalid room type: " + roomType);
        }

        if (availableRooms <= 0) {
            throw new InvalidBookingException("No rooms available for booking");
        }
    }
}

class Reservation {
    private String reservationId;
    private String roomType;

    public Reservation(String reservationId, String roomType) {
        this.reservationId = reservationId;
        this.roomType = roomType;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getRoomType() {
        return roomType;
    }
}

public class bookMyStayApp {
    public static void main(String[] args) {

        int availableRooms = 1;

        try {
            BookingValidator.validate("Deluxe", availableRooms);
            Reservation r1 = new Reservation("R201", "Deluxe");
            availableRooms--;

            System.out.println("Booking successful: " + r1.getReservationId());

        } catch (InvalidBookingException e) {
            System.out.println(e.getMessage());
        }

        try {
            BookingValidator.validate("Premium", availableRooms);
            Reservation r2 = new Reservation("R202", "Premium");
            availableRooms--;

            System.out.println("Booking successful: " + r2.getReservationId());

        } catch (InvalidBookingException e) {
            System.out.println(e.getMessage());
        }

        try {
            BookingValidator.validate("Suite", availableRooms);
            Reservation r3 = new Reservation("R203", "Suite");
            availableRooms--;

            System.out.println("Booking successful: " + r3.getReservationId());

        } catch (InvalidBookingException e) {
            System.out.println(e.getMessage());
        }
    }
}