import java.util.*;

/**
 * =================================================
 * CLASS - InvalidBookingException
 * =================================================
 * Custom exception for invalid booking scenarios
 */
class InvalidBookingException extends Exception {

    public InvalidBookingException(String message) {
        super(message);
    }
}

/**
 * =================================================
 * CLASS - RoomInventory
 * =================================================
 * Maintains available room types
 */
class RoomInventory {

    private Set<String> availableRooms;

    public RoomInventory() {
        availableRooms = new HashSet<>();
        availableRooms.add("Single");
        availableRooms.add("Double");
        availableRooms.add("Suite");
    }

    public boolean isRoomAvailable(String roomType) {
        return availableRooms.contains(roomType);
    }
}

/**
 * =================================================
 * CLASS - ReservationValidator
 * =================================================
 * Validates booking input
 */
class ReservationValidator {

    public void validate(
            String guestName,
            String roomType,
            RoomInventory inventory
    ) throws InvalidBookingException {

        if (guestName == null || guestName.trim().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty.");
        }

        if (!inventory.isRoomAvailable(roomType)) {
            throw new InvalidBookingException("Invalid room type selected.");
        }
    }
}

/**
 * =================================================
 * CLASS - BookingRequestQueue
 * =================================================
 * Stores booking requests
 */
class BookingRequestQueue {

    private Queue<String> requests;

    public BookingRequestQueue() {
        requests = new LinkedList<>();
    }

    public void addRequest(String guestName, String roomType) {
        requests.add("Guest: " + guestName + ", Room Type: " + roomType);
    }
}

/**
 * =================================================
 * MAIN CLASS - UseCase9ErrorHandlingValidation
 * =================================================
 * Demonstrates validation and error handling
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("Booking Validation");

        Scanner scanner = new Scanner(System.in);

        RoomInventory inventory = new RoomInventory();
        ReservationValidator validator = new ReservationValidator();
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        try {

            System.out.print("Enter guest name: ");
            String guestName = scanner.nextLine();

            System.out.print("Enter room type (Single/Double/Suite): ");
            String roomType = scanner.nextLine();

            validator.validate(guestName, roomType, inventory);

            bookingQueue.addRequest(guestName, roomType);

            System.out.println("Booking request added successfully.");

        } catch (InvalidBookingException e) {

            System.out.println("Booking failed: " + e.getMessage());

        } finally {
            scanner.close();
        }
    }
}