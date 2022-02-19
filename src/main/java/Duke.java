import java.util.Scanner;

public class Duke {
    private static String welcomeMessage() {
        return "Hello! I'm Duke\nWhat can I do for you?";
    }

    private static String farewellMessage() {
        return "Bye. Hope to see you again soon!";
    }

    public static void main(String[] args) {
        System.out.println(welcomeMessage());
        while(true) {
            String command = new Scanner(System.in).nextLine();
            if (command.trim().equalsIgnoreCase("bye")) {
                System.out.println(farewellMessage());
                break;
            } else {
                System.out.println(command);
            }
        }
    }
}
