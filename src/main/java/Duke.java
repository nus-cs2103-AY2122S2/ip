import java.util.*;

public class Duke {

    static Scanner scanner = new Scanner(System.in);
    static final String welcome_message = "Hello! I'm Duke\n" +
            "What can I do for you?";

    public static void main(String[] args) {

        System.out.println(welcome_message);

        while (scanner.hasNextLine()) {

            String input = scanner.nextLine().trim();
            String[] input_split = input.split(" ");

            switch (input_split[0]) {
                case "mark":
                    TaskFunctions.markTask(Integer.parseInt(input_split[1]));
                    break;
                case "unmark":
                    TaskFunctions.unmarkTask(Integer.parseInt(input_split[1]));
                    break;
                case "list":
                    TaskFunctions.showTaskList();
                    break;
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    System.exit(0);
                default:
                    TaskFunctions.addToList(new Task(input));
            }
        }
    }
}
