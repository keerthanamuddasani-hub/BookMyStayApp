import java.util.*;

/**
 * ==================================================
 * MAIN CLASS - UseCase7AddOnServiceSelection
 * ==================================================
 *
 * Use Case 7: Add-On Service Selection
 *
 * Description:
 * This class demonstrates how optional
 * services can be attached to a confirmed
 * booking.
 */

public class Main {

    public static void main(String[] args) {

        AddonServiceManager manager = new AddonServiceManager();

        String reservationId = "Single-1";

        AddonService breakfast = new AddonService("Breakfast", 50.0);
        AddonService spa = new AddonService("Spa", 100.0);

        manager.addService(reservationId, breakfast);
        manager.addService(reservationId, spa);

        double totalCost = manager.calculateTotalServiceCost(reservationId);

        System.out.println("Add-On Service Selection");
        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Total Add-On Cost: " + totalCost);
    }
}


/**
 * ==================================================
 * CLASS - AddonService
 * ==================================================
 *
 * Represents an optional service
 * that can be added to a reservation.
 */

class AddonService {

    private String serviceName;
    private double cost;

    public AddonService(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getCost() {
        return cost;
    }
}


/**
 * ==================================================
 * CLASS - AddonServiceManager
 * ==================================================
 *
 * Manages optional services for reservations
 */

class AddonServiceManager {

    private Map<String, List<AddonService>> servicesByReservation;

    public AddonServiceManager() {
        servicesByReservation = new HashMap<>();
    }

    public void addService(String reservationId, AddonService service) {

        if (!servicesByReservation.containsKey(reservationId)) {
            servicesByReservation.put(reservationId, new ArrayList<>());
        }

        servicesByReservation.get(reservationId).add(service);
    }

    public double calculateTotalServiceCost(String reservationId) {

        double total = 0;

        List<AddonService> services = servicesByReservation.get(reservationId);

        if (services != null) {
            for (AddonService service : services) {
                total += service.getCost();
            }
        }

        return total;
    }
}