import java.util.ArrayList;
import java.util.Scanner;

public class DukeTest {
    private static String exitTrigger = "bye";

    enum State {
        DONE, UNDONE
    };

    private static ArrayList<Task> taskList = new ArrayList<>();

    private static boolean isExit(String input) {
        return input.equals(exitTrigger);
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void addTask(String str) {
        taskList.add(new Task(str));
        System.out.println("added: " + str);
    }

    private static void list() {
        for (int i = 1; i <= taskList.size(); i++) {
            System.out.println(i + "." + taskList.get(i - 1).toString());
        }
    }

    private static void setState(int idx, State state) {
        switch (state) {
            case DONE:
                taskList.get(idx).mark();
                ;
                break;
            case UNDONE:
                taskList.get(idx).unmark();
                break;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        String input = scanner.nextLine();
        String[] splitWhiteSpace = new String[2];
        String command;
        splitWhiteSpace = input.split(" ");
        command = splitWhiteSpace[0];

        while (!(isExit(command))) {
            switch (command) {
                case "list":
                    list();
                    break;
                case "mark":
                    taskList.get(Integer.parseInt(splitWhiteSpace[1]) - 1).mark();
                    break;
                case "unmark":
                    taskList.get(Integer.parseInt(splitWhiteSpace[1]) - 1).unmark();
                    break;
                default:
                    addTask(input);
                    break;
            }

            input = scanner.nextLine();
            splitWhiteSpace = input.split(" ");
            command = splitWhiteSpace[0];
        }

        exit();
        scanner.close();
    }
}
