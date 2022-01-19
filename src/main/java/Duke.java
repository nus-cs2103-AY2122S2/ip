import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String TEXT_LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|";
    private static final String TEXT_GREETING = "Hello! I'm Duke\n" + "What can I do for you?";
    private static final String TEXT_GOODBYE = "Bye. Hope to see you again soon!";
    private static final String TEXT_DIVIDER = "____________________________________________________________";
    private static final String TEXT_ACKNOWLEDGE_LIST = "Here are the tasks in your list:";
    private static final String TEXT_ACKNOWLEDGE_MARK = "Nice! I've marked this task as done:";
    private static final String TEXT_ACKNOWLEDGE_UNMARK = "OK, I've marked this task as not done yet:";
    private static final String TEXT_ACKNOWLEDGE_DELETE = "Noted. I've removed this task:";
    private static final String TEXT_ACKNOWLEDGE_TASK = "Got it. I've added this task:";

    private static final String KEY_EXIT = "bye";
    private static final String KEY_LIST = "list";
    private static final String KEY_MARK = "mark";
    private static final String KEY_UNMARK = "unmark";
    private static final String KEY_DELETE = "delete";
    private static final String KEY_TODO = "todo";
    private static final String KEY_DEADLINE = "deadline";
    private static final String KEY_EVENT = "event";

    private final List<Task> tasks = new ArrayList<>();
    private boolean shouldExit = false;

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        greet();

        while (!shouldExit) {
            String input = scanner.nextLine();
            String[] tokens = input.split(" ", 2);
            String command = tokens[0];
            String[] params = new String[0];

            if (tokens.length > 1) {
                params = tokens[1].split("\\s/\\w\\w\\s", 2);
            }

            String[] paramsPadded = Arrays.copyOf(params, 2);

            try {
                processInput(command, paramsPadded);
            } catch (DukeException e) {
                printDivider();
                printTabbed(e.toString(), 1);
                printDivider();
                System.out.println();
            }
        }
    }

    private void processInput(String command, String[] params) {
        switch (command) {
        case KEY_EXIT:
            sayGoodbye();
            shouldExit = true;
            break;
        case KEY_LIST:
            listTasks();
            break;
        case KEY_MARK:
            markTask(Integer.parseInt(params[0]) - 1);
            break;
        case KEY_UNMARK:
            unmarkTask(Integer.parseInt(params[0]) - 1);
            break;
        case KEY_DELETE:
            deleteTask(Integer.parseInt(params[0]) - 1);
            break;
        case KEY_TODO:
            addTask(new ToDo(params[0]));
            break;
        case KEY_DEADLINE:
            addTask(new Deadline(params[0], params[1]));
            break;
        case KEY_EVENT:
            addTask(new Event(params[0], params[1]));
            break;
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private void markTask(int index) {
        tasks.get(index).setDone(true);
        printDivider();
        printTabbed(TEXT_ACKNOWLEDGE_MARK, 1);
        printTabbed(tasks.get(index).toString(), 3);
        printDivider();
        System.out.println();
    }

    private void unmarkTask(int index) {
        tasks.get(index).setDone(false);
        printDivider();
        printTabbed(TEXT_ACKNOWLEDGE_UNMARK, 1);
        printTabbed(tasks.get(index).toString(), 3);
        printDivider();
        System.out.println();
    }

    private void addTask(Task task) {
        tasks.add(task);
        printDivider();
        printTabbed(TEXT_ACKNOWLEDGE_TASK, 1);
        printTabbed(task.toString(), 3);
        printTabbed("Now you have " + tasks.size() + " tasks in the list.", 1);
        printDivider();
        System.out.println();
    }

    private void deleteTask(int index) {
        Task deleted = tasks.get(index);
        tasks.remove(index);
        printDivider();
        printTabbed(TEXT_ACKNOWLEDGE_DELETE, 1);
        printTabbed(deleted.toString(), 3);
        printTabbed("Now you have " + tasks.size() + " tasks in the list.", 1);
        printDivider();
        System.out.println();
    }

    private void listTasks() {
        printDivider();
        printTabbed(TEXT_ACKNOWLEDGE_LIST, 1);
        for (int i = 0; i < tasks.size(); i++) {
            String entry = (i + 1) + "." + tasks.get(i).toString();
            printTabbed(entry, 1);
        }

        printDivider();
        System.out.println();
    }

    private void greet() {
        printDivider();
        printTabbed(TEXT_LOGO, 1);
        System.out.println();
        printTabbed(TEXT_GREETING, 1);
        printDivider();
        System.out.println();
    }

    private void sayGoodbye() {
        printDivider();
        printTabbed(TEXT_GOODBYE, 1);
        printDivider();
        System.out.println();
    }

    private void printDivider() {
        printTabbed(TEXT_DIVIDER, 0);
    }

    private void printTabbed(String s, int padding) {
        String[] lines = s.split("\n");
        char[] whiteSpace = new char[padding];
        Arrays.fill(whiteSpace, ' ');

        for (String line : lines) {
            System.out.println('\t' + String.valueOf(whiteSpace) + line);
        }
    }
}
