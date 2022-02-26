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

    private static void taskAddedMessage(Task task) {
        System.out.println("Got it. I've added this task:\n  " + task
                + "\nNow you have " + taskList.size() + " tasks in the list.");
    }

    private static void addTaskToTaskList(Task task) {
        taskList.add(task);
        taskAddedMessage(task);
    }

    private static void viewTaskList() {
        System.out.println("Here are the tasks in your list:");
        ListIterator<Task> iterator = taskList.listIterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.nextIndex() + TURN_ZERO_BASED_INDEXING_TO_ONE_BASED_INDEXING + "."
                    + iterator.next());
        }
    }

    private static void taskDeletedMessage(Task task) {
        System.out.println("Noted. I've removed this task:\n  " + task +
                "\nNow you have " + taskList.size() + " tasks in the list.");
    }

    private static void deleteTask(int index) {
        Task taskToBeDeleted = taskList.get(index);
        taskList.remove(taskToBeDeleted);
        taskDeletedMessage(taskToBeDeleted);
    }

    public static void main(String[] args) {
        welcomeMessage();
        Scanner scanner = new Scanner(System.in);
        String command = "";
        while(!command.equals("BYE")) {
            command = scanner.next().toUpperCase();
            switch (command) {
            case "TODO":
                String todoTask = scanner.nextLine().trim();
                if (todoTask.isEmpty()) {
                    System.out.println("OOPS!!! The description of a todo cannot be empty.");
                } else {
                    addTaskToTaskList(new Todo(todoTask));
                }
                break;
            case "DEADLINE":
                String[] deadlineTask = scanner.nextLine().trim().split(" /by ");
                addTaskToTaskList(new Deadline(deadlineTask[0], deadlineTask[1]));
                break;
            case "EVENT":
                String[] eventTask = scanner.nextLine().trim().split(" /at ");
                addTaskToTaskList(new Event(eventTask[0], eventTask[1]));
                break;
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
            case "DELETE":
                int indexItemToBeDeleted = scanner.nextInt() + TURN_ONE_BASED_INDEXING_TO_ZERO_BASED_INDEXING;
                deleteTask(indexItemToBeDeleted);
                break;
            case "BYE":
                break;
            default:
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
        farewellMessage();
    }
}