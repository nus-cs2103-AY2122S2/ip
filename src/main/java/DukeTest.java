import java.util.ArrayList;
import java.util.Scanner;

public class DukeTest {
    private static String exitTrigger = "bye";

    private static ArrayList<Task> taskList = new ArrayList<>();

    private static boolean isExit(String input) {
        return input.equals(exitTrigger);
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void addTask(Task task) {
        System.out.println("Got it. I've added this task:\n" + task.toString());
        taskList.add(task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    private static void list() {
        for (int i = 1; i <= taskList.size(); i++) {
            System.out.println(i + "." + taskList.get(i - 1).toString());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        String input = scanner.nextLine();
        String[] splitInput = new String[2];
        String command;
        splitInput = input.split(" ", 2);
        command = splitInput[0];

        while (!(isExit(command))) {
            switch (command) {
                case "list":
                    list();
                    break;
                case "mark":
                    taskList.get(Integer.parseInt(splitInput[1]) - 1).mark();
                    break;
                case "unmark":
                    taskList.get(Integer.parseInt(splitInput[1]) - 1).unmark();
                    break;
                case "todo":
                    addTask(new ToDos(splitInput[1]));
                    break;
                case "deadline":
                    addTask(new Deadlines(splitInput[1]));
                    break;
                case "event":
                    addTask(new Events(splitInput[1]));
                    break;
                default:
                    break;
            }

            input = scanner.nextLine();
            splitInput = input.split(" ", 2);
            command = splitInput[0];
        }

        exit();
        scanner.close();
    }
}
