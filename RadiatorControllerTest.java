import java.util.Scanner;

// Enum for Signal
enum Signal {
    HIGH,
    LOW
}

// Class representing the state of RadiatorController
class RadiatorController {
    private Double temperature;
    private Signal fanStatus;

    // Constructor
    public RadiatorController() {
        this.temperature = null;
        this.fanStatus = null;
    }

    // Invariant
    public boolean inRange(Double val) {
        return val != null && (val >= 20.0 && val <= 80.0);
    }

    // Initialization
    public RadiatorController(Double temp, Signal status) {
        assert inRange(temp) : "Temperature should be in the range or equal to nil";
        assert status != null : "Fan status should be in the range or equal to nil";
        this.temperature = temp;
        this.fanStatus = status;
    }

    // Function to set temperature
    public void setTemperature(Double temp) {
        assert inRange(temp) : "Temperature should be in the range or equal to nil";
        assert temperature == null : "Temperature is already set";
        this.temperature = temp;
        setFanStatus(temp, fanStatus);
    }

    // Function to set fan status
    private void setFanStatus(Double temp, Signal status) {
        assert inRange(temp) : "Temperature should be in the range or equal to nil";
        assert temperature != null : "Temperature should be set before setting fan status";

        if (temp >= 80.0) {
            activateFan();
        } else if (temp <= 20.0) {
            deactivateFan();
        }
    }

    // Function to activate fan
    private void activateFan() {
        assert fanStatus == Signal.LOW : "Fan is already activated";
        fanStatus = Signal.HIGH;
    }

    // Function to deactivate fan
    private void deactivateFan() {
        assert fanStatus == Signal.HIGH : "Fan is already deactivated";
        fanStatus = Signal.LOW;
    }

    // Getter for temperature
    public Double getTemperature() {
        return temperature;
    }

    // Getter for fan status
    public Signal getFanStatus() {
        return fanStatus;
    }
}

class RadiatorControllerTest {
    public static void main(String[] args) {
        RadiatorController controller = new RadiatorController();
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("");
            System.out.println("=== Radiator Controller Menu ===");
            System.out.println("1. Set Temperature");
            System.out.println("2. Print Status");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    setTemperature(controller, scanner);
                    break;
                case 2:
                    printStatus(controller);
                    break;
                case 0:
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }

        } while (choice != 0);

        scanner.close();
    }

    private static void setTemperature(RadiatorController controller, Scanner scanner) {
        System.out.println("");
        System.out.print("Enter temperature: ");
        Double temp = scanner.nextDouble();
        controller.setTemperature(temp);
    }

    private static void printStatus(RadiatorController controller) {
        System.out.println("");
        System.out.println("=== Radiator Controller Status ===");
        System.out.println("Temperature: " + controller.getTemperature());

        if (controller.getTemperature() != null) {
            if (controller.getTemperature() >= 20.0 && controller.getTemperature() <= 80.0) {
                System.out.println("Fan Status: Normal");
            } else {
                System.out.println("Fan Status: " + controller.getFanStatus());
            }
        } else {
            System.out.println("Fan Status: Not available");
        }

        System.out.println();
    }
}
