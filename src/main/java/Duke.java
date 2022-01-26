import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String LINE = "_______________________________________________";
    private static final String INTRO = "\nHello! I'm YQ\n" + "What can I do for you?\n";
    private static final String LIST = "Here are the tasks in your list:";
    private static final String MARK = "Nice! I've marked this task as done:\n  ";
    private static final String UNMARK = "OK, I've marked this task as not done yet:\n  ";
    private static final String ADDED = "Got it. I've added this task:\n  ";
    private static final String REMOVED = "Noted. I've removed this task:\n";
    private static final String ERROR_UNKNOWN = "OOPS, I'm sorry, but I don't know what that means :-(";
    private static final String ERROR_DESCRIPTION = "OOPS, The description of a command cannot be empty.";

    public static void main(String[] args) throws DukeException {
        ArrayList<Task> database = new ArrayList<>();
        Duke.introMessage();
        Scanner sc = new Scanner(System.in);
        Task input = new Task(sc.nextLine());
        while (!input.description.equals("bye")) {
            try {
                System.out.println(LINE);
                if (input.description.equals("list")) {
                    Duke.listAction(database);
                } else if (input.description.startsWith("mark")) {
                    String itemNumber = input.description.substring(5);
                    Duke.markAction(itemNumber, database);
                } else if (input.description.startsWith("unmark")) {
                    String itemNumber = input.description.substring(7);
                    Duke.unmarkAction(itemNumber, database);
                } else if (input.description.startsWith("todo")) {
                    String description = input.description.substring(5);
                    Duke.toDoAction(description, database);
                } else if (input.description.startsWith("deadline")) {
                    String[] itemArr = input.description.substring(9).split(" /by ");
                    Duke.deadlineAction(itemArr, database);
                } else if (input.description.startsWith("event")) {
                    String[] itemArr = input.description.substring(6).split(" /at ");
                    Duke.eventAction(itemArr, database);
                } else if (input.description.startsWith("delete")) {
                    String itemNumber = input.description.substring(7);
                    deleteAction(itemNumber, database);
                } else {
                    throw new DukeException(ERROR_UNKNOWN);
                }
                System.out.println(LINE);
                input = new Task(sc.nextLine());
            } catch (StringIndexOutOfBoundsException e){
                System.out.println(new DukeException(ERROR_DESCRIPTION).getMessage() + "\n" + LINE);
            } catch (DukeException errorMessage) {
                System.out.println(errorMessage.getMessage() + "\n" + LINE);
            }
            input = new Task(sc.nextLine());
        }
        System.out.println(LINE + "\n" + "Bye. Hope to see you again soon!" + "\n" + LINE);
    }

    public static void introMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String intro = LINE + INTRO + logo + LINE;
        System.out.println(intro);
    }

    public static void listAction(ArrayList<Task> listOfItems) {
        System.out.println(LIST);
        for (int i = 0; i < listOfItems.size(); i++) {
            System.out.println(i + 1 + "." + listOfItems.get(i));
        }
    }

    public static void markAction(String taskNumber, ArrayList<Task> listOfItems) {
        int num = Integer.parseInt(taskNumber) - 1;
        Task taskToMark = listOfItems.get(num);
        if (!taskToMark.getStatusIcon().equals("X")) {
            taskToMark.markAsDone();
            listOfItems.set(num, taskToMark);
        }
        System.out.println(MARK + taskToMark);
    }

    public static void unmarkAction(String taskNumber, ArrayList<Task> listOfItems) {
        int num = Integer.parseInt(taskNumber) - 1;
        Task taskToUnmark = listOfItems.get(num);
        if (taskToUnmark.getStatusIcon().equals("X")) {
            taskToUnmark.markAsUndone();
            listOfItems.set(num, taskToUnmark);
        }
        System.out.println(UNMARK + taskToUnmark);
    }

    public static void toDoAction(String description, ArrayList<Task> lisOfItems) throws DukeException {
            Todo todoItem = new Todo(description);
            lisOfItems.add(todoItem);
            int numOfItems = lisOfItems.size();
            System.out.println(ADDED + todoItem);
            System.out.println("Now you have " + numOfItems + " tasks in the list.");
    }

    public static void deadlineAction(String[] arr, ArrayList<Task> listOfItems) {
        Deadline deadlineItem = new Deadline(arr[0], arr[1]);
        listOfItems.add(deadlineItem);
        int numOfItems = listOfItems.size();
        System.out.println(ADDED + deadlineItem);
        System.out.println("Now you have " + numOfItems + " tasks in the list.");
    }

    public static void eventAction(String[] arr, ArrayList<Task> listOfItems) {
        Event eventItem = new Event(arr[0], arr[1]);
        listOfItems.add(eventItem);
        int numOfItems = listOfItems.size();
        System.out.println(ADDED + eventItem);
        System.out.println("Now you have " + numOfItems + " tasks in the list.");
    }

    public static void deleteAction(String taskNumber, ArrayList<Task> listOfItems) {
        int num = Integer.parseInt(taskNumber) - 1;
        Task itemToDelete = listOfItems.get(num);
        listOfItems.remove(num);
        int numOfItems = listOfItems.size();
        System.out.println(REMOVED + itemToDelete);
        System.out.println("Now you have " + numOfItems + " tasks in the list.");
    }
}
