import java.util.*;

/* Reservation class */
class Reservation {
    String guestName;
    String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

/* Booking Request Queue */
class BookingRequestQueue {

    private Queue<Reservation> queue = new LinkedList<>();

    public void addReservation(Reservation r) {
        queue.add(r);
    }

    public Reservation getNextReservation() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}

/* Room Inventory */
class RoomInventory {

    Map<String, Integer> rooms = new HashMap<>();

    public RoomInventory() {
        rooms.put("Single", 3);
        rooms.put("Double", 2);
        rooms.put("Suite", 1);
    }

    public boolean allocateRoom(String roomType) {
        int count = rooms.getOrDefault(roomType, 0);
        if (count > 0) {
            rooms.put(roomType, count - 1);
            return true;
        }
        return false;
    }

    public void printInventory() {
        System.out.println("\nRemaining Inventory:");
        for (String type : rooms.keySet()) {
            System.out.println(type + ": " + rooms.get(type));
        }
    }
}

/* Room Allocation Service */
class RoomAllocationService {

    public void allocateRoom(Reservation reservation, RoomInventory inventory) {

        boolean success = inventory.allocateRoom(reservation.roomType);

        if (success) {
            System.out.println("Booking confirmed for Guest: "
                    + reservation.guestName
                    + ", Room ID: "
                    + reservation.roomType);
        } else {
            System.out.println("No rooms available for "
                    + reservation.roomType
                    + " for guest "
                    + reservation.guestName);
        }
    }
}

/* Concurrent Booking Processor */
class ConcurrentBookingProcessor implements Runnable {

    private BookingRequestQueue bookingQueue;
    private RoomInventory inventory;
    private RoomAllocationService allocationService;

    public ConcurrentBookingProcessor(
            BookingRequestQueue bookingQueue,
            RoomInventory inventory,
            RoomAllocationService allocationService) {

        this.bookingQueue = bookingQueue;
        this.inventory = inventory;
        this.allocationService = allocationService;
    }

    @Override
    public void run() {

        while (true) {

            Reservation reservation;

            synchronized (bookingQueue) {

                if (bookingQueue.isEmpty()) {
                    break;
                }

                reservation = bookingQueue.getNextReservation();
            }

            synchronized (inventory) {
                allocationService.allocateRoom(reservation, inventory);
            }
        }
    }
}

/* MAIN CLASS */
public class Main {

    public static void main(String[] args) {

        BookingRequestQueue bookingQueue = new BookingRequestQueue();
        RoomInventory inventory = new RoomInventory();
        RoomAllocationService allocationService = new RoomAllocationService();

        /* Add booking requests */
        bookingQueue.addReservation(new Reservation("Adhi", "Single"));
        bookingQueue.addReservation(new Reservation("Varun", "Double"));
        bookingQueue.addReservation(new Reservation("Kunal", "Suite"));
        bookingQueue.addReservation(new Reservation("Subha", "Single"));

        System.out.println("Concurrent Booking Simulation\n");

        /* Create booking processor tasks */

        Thread t1 = new Thread(
                new ConcurrentBookingProcessor(
                        bookingQueue,
                        inventory,
                        allocationService
                )
        );

        Thread t2 = new Thread(
                new ConcurrentBookingProcessor(
                        bookingQueue,
                        inventory,
                        allocationService
                )
        );

        /* Start concurrent processing */

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Thread execution interrupted.");
        }

        inventory.printInventory();
    }
}