import java.util.Scanner;

/**
 * Main Chat interface class.
 */
public class Chat {
    Scanner scanner = new Scanner(System.in);

    /**
     * Starts the chat between Duke and the User.
     */
    public void start() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        String command = scanner.next();
        while (!command.equals("bye")) {
            //perform action related to the command here
            System.out.println(command);

            //get next command
            command = scanner.next();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
