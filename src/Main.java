public class Main {

    public static void main(String[] args) {

        // Create room objects using polymorphism
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        // Availability variables (simple state representation)
        int singleRoomAvailable = 5;
        int doubleRoomAvailable = 3;
        int suiteRoomAvailable = 2;

        System.out.println("----- Hotel Room Information -----\n");

        singleRoom.displayDetails();
        System.out.println("Available Rooms: " + singleRoomAvailable);
        System.out.println();

        doubleRoom.displayDetails();
        System.out.println("Available Rooms: " + doubleRoomAvailable);
        System.out.println();

        suiteRoom.displayDetails();
        System.out.println("Available Rooms: " + suiteRoomAvailable);
        System.out.println();

        System.out.println("Application terminated.");
    }
}

// Abstract Class
abstract class Room {

    protected String roomType;
    protected int beds;
    protected int size;
    protected double pricePerNight;

    public Room(String roomType, int beds, int size, double pricePerNight) {
        this.roomType = roomType;
        this.beds = beds;
        this.size = size;
        this.pricePerNight = pricePerNight;
    }

    // Common behavior
    public void displayDetails() {
        System.out.println("Room Type: " + roomType);
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sq ft");
        System.out.println("Price Per Night: $" + pricePerNight);
    }
}

// Single Room
class SingleRoom extends Room {

    public SingleRoom() {
        super("Single Room", 1, 200, 100.0);
    }
}

// Double Room
class DoubleRoom extends Room {

    public DoubleRoom() {
        super("Double Room", 2, 350, 180.0);
    }
}

// Suite Room
class SuiteRoom extends Room {

    public SuiteRoom() {
        super("Suite Room", 3, 600, 350.0);
    }
}