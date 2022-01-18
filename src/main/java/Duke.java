import javafx.concurrent.Task;

import java.util.Scanner;

public class Duke {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String INDENT = "";
    private static final TaskList taskList = new TaskList();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Duke.greet();

        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                Duke.bye();
                break;
            } else if (input.equals("list")){
                Duke.log(taskList.getTasks());
            } else {
                Duke.log(taskList.addTask(input));
            }
        }
    }

    public static void greet() {
        System.out.println(Duke.INDENT + Duke.HORIZONTAL_LINE);
        System.out.println(Duke.INDENT + "Hello there, I am Giga Chad Duke");
        System.out.println(Duke.INDENT + "How can I help you?");
        System.out.println(Duke.INDENT + Duke.HORIZONTAL_LINE);
    }

    public static void bye() {
        System.out.println(Duke.INDENT + Duke.HORIZONTAL_LINE);
        System.out.println(Duke.INDENT + "Bye, hope to see you again soon!");
        System.out.println(Duke.INDENT + Duke.HORIZONTAL_LINE);
    }

    public static void log(String args) {
        System.out.println(Duke.INDENT + Duke.HORIZONTAL_LINE);
        System.out.println(Duke.INDENT + args);
        System.out.println(Duke.INDENT + Duke.HORIZONTAL_LINE);
    }
}
