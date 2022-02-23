import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class Duke {
    private static final int TURN_ZERO_BASED_INDEXING_TO_ONE_BASED_INDEXING = 1;
    private static final int TURN_ONE_BASED_INDEXING_TO_ZERO_BASED_INDEXING = -1;

    private static final ArrayList<Task> taskList = new ArrayList<>(100);

    private static void welcomeMessage() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    private static void farewellMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void viewTaskList() {
        System.out.println("Here are the tasks in your list:");
        ListIterator<Task> iterator = taskList.listIterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.nextIndex() + TURN_ZERO_BASED_INDEXING_TO_ONE_BASED_INDEXING + "."
                    + iterator.next());
        }
    }

    public static void main(String[] args) {
        welcomeMessage();
        String command = "";
        while(!command.equals("BYE")) {
            Scanner scanner = new Scanner(System.in);
            command = scanner.next().toUpperCase();
            switch (command) {
            case "LIST":
                viewTaskList();
                break;
            case "MARK":
                int indexItemToBeMarked = scanner.nextInt() + TURN_ONE_BASED_INDEXING_TO_ZERO_BASED_INDEXING;
                taskList.get(indexItemToBeMarked).markTaskAsDone();
                break;
            case "UNMARK":
                int indexItemToBeUnmarked = scanner.nextInt() + TURN_ONE_BASED_INDEXING_TO_ZERO_BASED_INDEXING;
                taskList.get(indexItemToBeUnmarked).markTaskAsUndone();
                break;
            case "BYE":
                break;
            default:
                String taskToBeAdded = command + scanner.nextLine().toUpperCase();
                taskList.add(new Task(taskToBeAdded));
                System.out.println("added: " + taskToBeAdded);
            }
        }
        farewellMessage();
    }
}