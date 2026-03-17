import java.io.*;
import java.util.*;

/*
 ======================================================
 CLASS - RoomInventory
 ======================================================
 Stores available room counts
*/
class RoomInventory {

    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
    }

    public void setRoomCount(String roomType, int count) {
        inventory.put(roomType, count);
    }

    public int getRoomCount(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public Map<String, Integer> getAllRooms() {
        return inventory;
    }
}

/*
 ======================================================
 CLASS - FilePersistenceService
 ======================================================
 Saves and loads inventory data from file
*/
class FilePersistenceService {

    /*
     Saves inventory state to file
     Format: roomType=availableCount
    */
    public void saveInventory(RoomInventory inventory, String filePath) {

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {

            for (Map.Entry<String, Integer> entry :
                    inventory.getAllRooms().entrySet()) {

                writer.println(entry.getKey() + "=" + entry.getValue());
            }

            System.out.println("Inventory saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving inventory: " + e.getMessage());
        }
    }

    /*
     Loads inventory state from file
    */
    public void loadInventory(RoomInventory inventory, String filePath) {

        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("No valid inventory data found. Starting fresh.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split("=");

                if (parts.length == 2) {

                    String roomType = parts[0];
                    int count = Integer.parseInt(parts[1]);

                    inventory.setRoomCount(roomType, count);
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading inventory: " + e.getMessage());
        }
    }
}

/*
 ======================================================
 MAIN CLASS - UseCase12DataPersistenceRecovery
 ======================================================
 Demonstrates system recovery using file persistence
*/
public class Main {

    public static void main(String[] args) {

        System.out.println("System Recovery");

        RoomInventory inventory = new RoomInventory();
        FilePersistenceService persistence =
                new FilePersistenceService();

        String filePath = "inventory.txt";

        // Load existing inventory
        persistence.loadInventory(inventory, filePath);

        // If inventory empty, initialize default values
        if (inventory.getAllRooms().isEmpty()) {

            inventory.setRoomCount("Single", 5);
            inventory.setRoomCount("Double", 3);
            inventory.setRoomCount("Suite", 2);
        }

        System.out.println("\nCurrent Inventory:");

        for (Map.Entry<String, Integer> entry :
                inventory.getAllRooms().entrySet()) {

            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Save inventory
        persistence.saveInventory(inventory, filePath);
    }
}