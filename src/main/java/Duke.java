import java.util.*;

public class Duke {

    static Scanner scanner = new Scanner(System.in);
    static final String welcome_message = "Hello! I'm Duke\n" +
            "What can I do for you?";
    static final int SIZE = 100;
    static String[] taskList = new String[SIZE];
    static int pointer = 0;

    public static void main(String[] args) {

        System.out.println(welcome_message);

        while (scanner.hasNext()) {
            String input = scanner.nextLine();

            switch (input) {
                case "list":
                    if (pointer == 0) {
                        System.out.println("You have not yet added any tasks!");
                        break;
                    }
                    else {
                        for (int i = 0; i < pointer; i++) {
                            System.out.println(i + 1 + ". " + taskList[i]);
                        }
                    }
                    break;
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    System.exit(0);
                default:
                    taskList[pointer++] = input;
                    System.out.println("added: " + input);
            }
        }
    }
}
