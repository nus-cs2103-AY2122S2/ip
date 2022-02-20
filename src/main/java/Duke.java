import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class Duke {
    private static final int TURN_ZERO_BASED_INDEXING_TO_ONE_BASED_INDEXING = 1;

    private static final ArrayList<String> taskList = new ArrayList<>(100);

    private static void welcomeMessage() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    private static void farewellMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void viewTaskList() {
        ListIterator<String> iterator = taskList.listIterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.nextIndex() + TURN_ZERO_BASED_INDEXING_TO_ONE_BASED_INDEXING
                    + ". " + iterator.next());
        }
    }

    public static void main(String[] args) {
        welcomeMessage();
        while(true) {
            String command = new Scanner(System.in).nextLine();
            if (command.equalsIgnoreCase("bye")) {
                farewellMessage();
                break;
            } else if (command.equalsIgnoreCase("list")){
                viewTaskList();
            } else {
                taskList.add(command);
                System.out.println("added: " + command);
            }
        }
    }
}