import java.util.HashMap;
import java.util.Map;

/*
 * Class: CustomerService
 * Use Case ID: Booking Cancellation & Inventory Rollback
 *
 * Description:
 * This class is responsible for handling
 * booking registrations and cancellations.
 */

class CustomerService {

    // Maximum number of tickets allowed per booking
    private static final int MAX_TICKETS = 5;

    // Stores bookings
    private Map<Integer, String> reservations = new HashMap<>();

    // Constructor
    public CustomerService() {
        System.out.println("Customer Service initialized...");
    }

    /*
     * Registers a customer booking
     */
    public void registerBooking(int reservationId, String customerName) {

        if (reservations.size() >= MAX_TICKETS) {
            System.out.println("Maximum booking limit reached.");
            return;
        }

        reservations.put(reservationId, customerName);

        System.out.println("Booking registered successfully for "
                + customerName + " with reservation ID: " + reservationId);
    }

    /*
     * Cancels a booking
     */
    public void cancelBooking(int reservationId) {

        if (reservations.containsKey(reservationId)) {
            reservations.remove(reservationId);
            System.out.println("Booking cancelled successfully for reservation ID: " + reservationId);
        } else {
            System.out.println("Reservation not found.");
        }
    }

    /*
     * Shutdown service
     */
    public void shutdownService() {
        System.out.println("System shutting down...");
    }
}


/*
 * Class: AccessBookingCancellation
 *
 * Description:
 * This class demonstrates how booking cancellation
 * is handled in the system.
 */

class AccessBookingCancellation {

    public static void main(String[] args) {

        CustomerService service = new CustomerService();

        // Register bookings
        service.registerBooking(1001, "Rahul");
        service.registerBooking(1002, "Ananya");

        // Cancel booking
        service.cancelBooking(1001);

        // Shutdown system
        service.shutdownService();
    }
}