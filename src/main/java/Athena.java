import java.util.Scanner;

public class Athena {
    public static void main(String[] args) {
        // Initialize variables
        Scanner scanner = new Scanner(System.in);
        TrackingList trackingList = new TrackingList();
        boolean isActive = true;
        sayText("Hello, my name is Athena. What can I help you with?");

        while (isActive) {
            System.out.print("Enter command: ");
            String command = scanner.nextLine();
            switch (command) {
                case "list":
                    sayText("These are the list contents:");
                    System.out.println(trackingList);
                    break;
                case "bye":
                    isActive = false;
                    break;
                default:
                    trackingList.addToList(command);
                    sayText("I've added \"" + command + "\" to the list.");
                    break;
            }
        }
        sayText("Goodbye!");
    }

    private static void sayText(String text) {
        System.out.println("Athena: " + text);
    }
}
