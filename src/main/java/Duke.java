import java.util.*;
import java.io.*;

public class Duke {

    public static final String hl = "------------------------------------------------------------------------";
    public static ArrayList<Task> taskList = new ArrayList<Task>();

    public static void greetings() {
        String logo = "";
        System.out.println(hl + "\nHi! I'm Duke\nWhat can I do for you?");
    }

    public static void add(String[] instructions) {
        Task task = null;
        if (instructions[0].equals("todo")) {
            task = new Todo(instructions[1], taskList.size() + 1);
            System.out.println("Added a to do task.");
        } else if (instructions[0].equals("deadline")) {
            String[] taskAndTime = instructions[1].split("/by");
            task = new Deadline(taskAndTime[0], taskList.size() + 1, taskAndTime[1]);
            System.out.println("Added a deadline.");
        } else if (instructions[0].equals("event")) {
            String[] taskAndTime = instructions[1].split("/at");
            task = new Event(taskAndTime[0], taskList.size() + 1, taskAndTime[1]);
            System.out.println("Added an event.");
        }
        if (task != null) {
            taskList.add(task);
            System.out.println("  " + task);
            System.out.println("You have " + taskList.size() + " task(s) in the list.");
        }
    }

    public static void list() {
        if (taskList.size() == 0) {
            System.out.println("You have no tasks!");
        } else {
            System.out.println("The tasks on your list. Get it done!");
            for (Task task : taskList) {
                System.out.println("  " + task);
            }
        }
    }

    public static void mark(int taskNumber) {
        Task task = taskList.get(taskNumber - 1);
        task.markAsDone();
        System.out.println("Good job! This task is done:");
        System.out.println("  " + task);
    }

    public static void unmark(int taskNumber) {
        Task task = taskList.get(taskNumber - 1);
        task.unmark();
        System.out.println("Hurry up and get it done!");
        System.out.println("  " + task);
    }

    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(hl);
    }

    public static void checkCommand(String[] instructions) throws DukeException {
        if (instructions.length == 0) {
            throw new DukeException("Empty input.");
        } else {
            String command = instructions[0];
            if (command.equals("bye") || command.equals("list")) {
                if (instructions.length > 1) {
                    throw new DukeException("There's too many input!");
                }
            } else if (command.equals("mark") || command.equals("unmark")) {
                if (instructions.length == 1) {
                    throw new DukeException("Please specify the task number to " + command + ".");
                }
                int taskNumber = Integer.parseInt(instructions[1]);
                if (taskNumber > taskList.size() || taskNumber <= 0) {
                    throw new DukeException("Invalid task number! You have " + taskList.size() + " task(s).");
                }
            } else if (command.equals("todo")) {
                if (instructions.length == 1) {
                    throw new DukeException("Please specify the task you want to do.");
                }
            } else if (command.equals("deadline") || command.equals("event")) {
                if (instructions.length == 1) {
                    throw new DukeException("Please specify the description and time of the " + command + " you want to add.");
                }
                String[] taskAndTime = instructions[1].split("/");
                if (taskAndTime.length < 2 || taskAndTime[0].equals("") || taskAndTime[1].length() < 3) {
                    throw new DukeException("Invalid input. Please specify the description/time of the " + command + ".");
                }
            } else {
                throw new DukeException("I don't understand your query.");
            }
        }
    }

    public static void chat() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println(hl);
            System.out.print("> ");
            String userInput = br.readLine();
            String[] instructions = userInput.split(" ", 2);
            try {
                checkCommand(instructions);
                if (instructions[0].equals("bye")) {
                    break;
                } else if (instructions[0].equals("list")) {
                    list();
                } else if (instructions[0].equals("mark")) {
                    int taskNumber = Integer.parseInt(instructions[1]);
                    mark(taskNumber);
                } else if (instructions[0].equals("unmark")) {
                    int taskNumber = Integer.parseInt(instructions[1]);
                    unmark(taskNumber);
                } else {
                    add(instructions);
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        greetings();
        chat();
        bye();
    }
}
