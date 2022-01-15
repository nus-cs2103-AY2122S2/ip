import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main Chat interface class.
 */
public class Chat {
    ArrayList<String> list = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    /**
     * Starts the chat between Duke and the User.
     */
    public void start() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        String command = scanner.next();
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                //print out all items in list, numbered from 1
                for (int i = 1; i < list.size() + 1; i++) {
                    System.out.println(String.valueOf(i) + ". " + list.get(i - 1));
                }
            } else {
                //perform action related to the command here
                System.out.println("added: " + command);
                list.add(command);
            }
            //get next command
            command = scanner.next();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
