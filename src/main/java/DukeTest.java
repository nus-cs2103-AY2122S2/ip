import java.util.ArrayList;
import java.util.Scanner;

public class DukeTest {
    private static ArrayList<Task> taskList = new ArrayList<>();

    private static boolean isExit(String input) {
        return input.equals("bye");
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void addTask(Task task) {
        taskList.add(task);
        System.out.println("Got it. I've added this task:\n" + task.toString() +
                "\nNow you have " + taskList.size() + " tasks in the list.");
    }

    private static void deleteTask(int idx) {
        Task temp = taskList.get(idx - 1);
        taskList.remove(idx - 1);
        System.out.println("Noted. I've removed this task:\n" + temp.toString() +
                "\nNow you have " + taskList.size() + " tasks in the list.");

    }

    private static void list() {
        for (int i = 1; i <= taskList.size(); i++) {
            System.out.println(i + "." + taskList.get(i - 1).toString());
        }
    }

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Hello! I'm Duke\nWhat can I do for you?");

            String input = scanner.nextLine();

            String[] splitInput = input.split(" ", 2);
            ;
            String command = splitInput[0];

            while (!(isExit(command))) {
                switch (command) {
                    case "list":
                        if (splitInput.length > 1) {
                            throw new DukeException("There should not be anything else after list.");
                        } else {
                            list();
                            break;
                        }
                    case "mark":
                        if (splitInput.length < 2) {
                            throw new DukeException("Please indicate which task you want to mark!");
                        } else {
                            taskList.get(Integer.parseInt(splitInput[1]) - 1).mark();
                            break;
                        }
                    case "unmark":
                        if (splitInput.length < 2) {
                            throw new DukeException("Please indicate which task you want to unmark!");
                        } else {
                            taskList.get(Integer.parseInt(splitInput[1]) - 1).unmark();
                            break;
                        }
                    case "todo":
                        if (splitInput.length < 2) {
                            throw new DukeException("The description of a todo cannot be empty.");
                        } else {
                            addTask(new ToDos(splitInput[1]));
                            break;
                        }
                    case "deadline":
                        if (splitInput.length < 2) {
                            throw new DukeException("The description of a deadline cannot be empty.");
                        } else {
                            addTask(new Deadlines(splitInput[1]));
                            break;
                        }
                    case "event":
                        if (splitInput.length < 2) {
                            throw new DukeException("The description of an event cannot be empty.");
                        } else {
                            addTask(new Events(splitInput[1]));
                            break;
                        }
                    case "delete":
                        deleteTask(Integer.parseInt(splitInput[1]));
                        break;
                    default:
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }

                input = scanner.nextLine();
                splitInput = input.split(" ", 2);
                command = splitInput[0];
            }

            exit();
            scanner.close();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
