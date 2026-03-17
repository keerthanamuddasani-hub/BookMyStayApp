import java.util.*;

/**
 * ==================================================
 * CLASS - Reservation
 * ==================================================
 * Represents a confirmed reservation
 */

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


/**
 * ==================================================
 * CLASS - BookingHistory
 * ==================================================
 *
 * Use Case 8: Booking History & Reporting
 *
 * This class maintains a record of
 * confirmed reservations.
 */

class BookingHistory {

    /* List that stores confirmed reservations */
    private List<Reservation> confirmedReservations;

    /* Initializes an empty booking history */
    public BookingHistory() {
        confirmedReservations = new ArrayList<>();
    }

    /* Adds a confirmed reservation */
    public void addReservation(Reservation reservation) {
        confirmedReservations.add(reservation);
    }

    /* Returns all confirmed reservations */
    public List<Reservation> getConfirmedReservations() {
        return confirmedReservations;
    }
}


/**
 * ==================================================
 * CLASS - BookingReportService
 * ==================================================
 *
 * Generates reports from booking history data
 */

class BookingReportService {

    /* Displays booking history report */

    public void generateReport(BookingHistory history) {

        System.out.println("Booking History and Reporting\n");
        System.out.println("Booking History Report");

        for (Reservation r : history.getConfirmedReservations()) {

            System.out.println(
                    "Guest: " + r.getGuestName() +
                            ", Room Type: " + r.getRoomType()
            );
        }
    }
}


/**
 * ==================================================
 * MAIN CLASS - UseCase8BookingHistoryReport
 * ==================================================
 *
 * Demonstrates storing and reporting booking history
 */

public class Main {

    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();

        history.addReservation(new Reservation("Abhi", "Single"));
        history.addReservation(new Reservation("Subha", "Double"));
        history.addReservation(new Reservation("Vannathi", "Suite"));

        BookingReportService reportService = new BookingReportService();

        reportService.generateReport(history);
    }
}