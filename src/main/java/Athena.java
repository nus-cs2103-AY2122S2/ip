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
            String rawInput = scanner.nextLine();
            String[] splitInput = rawInput.split(" ");
            switch (splitInput[0]) {
                case "mark": // assume no user errors
                    int taskNumber = Integer.parseInt(splitInput[1]);
                    sayText("Alright, I've marked the following task as done:");
                    System.out.println(trackingList.markTaskAsDone(taskNumber));
                    break;
                case "unmark":
                    taskNumber = Integer.parseInt(splitInput[1]);
                    sayText("Alright, I've marked the following task as not done:");
                    System.out.println(trackingList.markTaskAsNotDone(taskNumber));
                    break;
                case "list":
                    sayText("These are the list contents:");
                    System.out.println(trackingList);
                    break;
                case "bye":
                    isActive = false;
                    break;
                default:
                    trackingList.addToList(rawInput);
                    sayText("I've added \"" + rawInput + "\" to the list.");
                    break;
            }
        }
        sayText("Goodbye!");
    }

    private static void sayText(String text) {
        System.out.println("Athena: " + text);
    }
}
