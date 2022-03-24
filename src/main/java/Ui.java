import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class Ui {
    private static final int TURN_ZERO_BASED_INDEXING_TO_ONE_BASED_INDEXING = 1;
    public final int TURN_ONE_BASED_INDEXING_TO_ZERO_BASED_INDEXING = -1;

    private final Scanner scanner = new Scanner(System.in);

    public String readCommand() {
        return scanner.nextLine();
    }

    public void welcomeMessage() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void farewellMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void taskAddedMessage(ArrayList<Task> taskList, Task task) {
        System.out.println("Got it. I've added this task:\n  " + task
                + "\nNow you have " + taskList.size() + " tasks in the list.");
    }

    public static void taskDeletedMessage(ArrayList<Task> taskList, Task task) {
        System.out.println("Noted. I've removed this task:\n  " + task
                + "\nNow you have " + taskList.size() + " tasks in the list.");
    }

    public static void viewTaskListMessage(ArrayList<Task> taskList) {
        System.out.println("Here are the tasks in your list:");
        ListIterator<Task> iterator = taskList.listIterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.nextIndex() + TURN_ZERO_BASED_INDEXING_TO_ONE_BASED_INDEXING + "."
                    + iterator.next());
        }
    }

    public void emptyTodoDescriptionErrorMessage() {
        System.out.println("OOPS!!! The description of a todo cannot be empty.");
    }

    public void emptyDeadlineDescriptionErrorMessage() {
        System.out.println("OOPS!!! The description of a deadline cannot be empty.");
    }

    public void emptyEventDescriptionErrorMessage() {
        System.out.println("OOPS!!! The description of a event cannot be empty.");
    }

    public void wrongCommandErrorMessage() {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
