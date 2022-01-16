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
            } else {
                echo(input);
            }
        }
    }

    private void greet() {
        System.out.println(indent(addSeparator(TEXT_LOGO + "\n\n" + TEXT_GREETING)));
    }

    private void echo(String input) {
        System.out.println(indent(addSeparator(input)));
    }

    private void sayGoodbye() {
        System.out.println(indent(addSeparator(TEXT_GOODBYE)));
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
