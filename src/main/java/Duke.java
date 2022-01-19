import java.util.*;

public class Duke {

    static Scanner scanner = new Scanner(System.in);
    static final String welcome_message = "Hello! I'm Duke\n" +
            "What can I do for you?";

    public static void main(String[] args) {

        System.out.println(welcome_message);

        while (scanner.hasNext()) {
            String input = scanner.nextLine();

            switch (input) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    System.exit(0);
                default:
                    System.out.println(input);
            }
        }
    }
}
