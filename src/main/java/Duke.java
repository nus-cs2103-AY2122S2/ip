import java.util.ArrayList;
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
        System.out.println(indent(addSeparator("added: " + input)) + '\n');
    }

    private void listTasks() {
        System.out.println(indent(TEXT_DIVIDER));

        for (int i = 0; i < tasks.size(); i++) {
            String entry = (i + 1) + ". " + tasks.get(i);
            System.out.println(indent(entry));
        }

        System.out.println(indent(TEXT_DIVIDER) + '\n');
    }

    private void greet() {
        System.out.println(indent(addSeparator(TEXT_LOGO + "\n\n" + TEXT_GREETING)) + '\n');
    }

    private void sayGoodbye() {
        System.out.println(indent(addSeparator(TEXT_GOODBYE)) + '\n');
    }

    private String addSeparator(String input) {
        return TEXT_DIVIDER + '\n' + input + '\n' + TEXT_DIVIDER;
    }

    private String indent(String input) {
        StringBuilder sb = new StringBuilder(input).insert(0, '\t');

        for (int i = 0; i < sb.length() - 1; i++) {
            if (sb.charAt(i) == '\n') {
                sb.insert(i + 1, '\t');
            }
        }

        return sb.toString();
    }
}
