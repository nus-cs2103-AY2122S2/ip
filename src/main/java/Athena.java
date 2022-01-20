import java.util.Scanner;

public class Athena {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        sayText("Hello, my name is Athena. What can I help you with?");
        boolean isActive = true;
        while (isActive) {
            System.out.print("Enter command: ");
            String command = scanner.nextLine();
            switch (command) {
                case "bye":
                    isActive = false;
                    break;
                default:
                    sayText(command);
                    break;
            }
        }
        sayText("Goodbye!");
    }
    private static void sayText(String text) {
        System.out.println("Athena: " + text);
    }
}
