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
    private static final String KEY_EXIT = "bye";
    private static final String KEY_LIST = "list";

    private final List<String> tasks = new ArrayList<>();

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        greet();

        while (true) {
            String input = scanner.nextLine();

            if (input.equals(KEY_EXIT)) {
                sayGoodbye();
                return;
            } else if (input.equals(KEY_LIST)) {
                listTasks();
            } else {
                addTask(input);
            }
        }
    }

    private void addTask(String input) {
        tasks.add(input);
        printDivider();
        printTabbed("added: " + input, 1);
        printDivider();
        System.out.println();
    }

    private void listTasks() {
        printDivider();

        for (int i = 0; i < tasks.size(); i++) {
            String entry = (i + 1) + ". " + tasks.get(i);
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
