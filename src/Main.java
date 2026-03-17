import java.util.LinkedList;
import java.util.Queue;

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
        System.out.println("Guest: " + guestName + " | Room Type: " + roomType);
    }
}

class BookingRequestQueue {

    private Queue<Reservation> bookingQueue;

    public BookingRequestQueue() {
        bookingQueue = new LinkedList<>();
    }

    public void addRequest(Reservation reservation) {
        bookingQueue.offer(reservation);
    }

    public void displayQueue() {
        for (Reservation r : bookingQueue) {
            r.displayReservation();
        }
    }
}

public class Main {

    public static void main(String[] args) {

        BookingRequestQueue requestQueue = new BookingRequestQueue();

        requestQueue.addRequest(new Reservation("Alice", "Single Room"));
        requestQueue.addRequest(new Reservation("Bob", "Double Room"));
        requestQueue.addRequest(new Reservation("Charlie", "Suite Room"));

        System.out.println("Current Booking Requests (FIFO Order):");
        requestQueue.displayQueue();
    }
}