import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String TEXT_LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|";
    private static final String TEXT_GREETING = "Hello! I'm Duke\n" + "What can I do for you?";
    private static final String TEXT_GOODBYE = "Bye. Hope to see you again soon!";
    private static final String TEXT_DIVIDER = "____________________________________________________________";
    private static final String TEXT_ACKNOWLEDGE_LIST = "Here are the tasks in your list:";
    private static final String TEXT_ACKNOWLEDGE_MARK = "Nice! I've marked this task as done:";
    private static final String TEXT_ACKNOWLEDGE_UNMARK = "OK, I've marked this task as not done yet:";

    private static final String KEY_EXIT = "bye";
    private static final String KEY_LIST = "list";
    private static final String KEY_MARK = "mark";
    private static final String KEY_UNMARK = "unmark";

    private final List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        greet();

        while (true) {
            String input = scanner.nextLine();
            String[] strings = input.split(" ", 2);
            String command = strings[0];

            switch (command) {
            case KEY_EXIT:
                sayGoodbye();
                return;
            case KEY_LIST:
                listTasks();
                break;
            case KEY_MARK:
                markTask(Integer.parseInt(strings[1]) - 1);
                break;
            case KEY_UNMARK:
                unmarkTask(Integer.parseInt(strings[1]) - 1);
                break;
            default:
                addTask(input);
                break;
            }
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

    private void addTask(String input) {
        tasks.add(new Task(input));
        printDivider();
        printTabbed("added: " + input, 1);
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
