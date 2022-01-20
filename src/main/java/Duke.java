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
        } else {
            task = new Task(String.join(" ", instructions), taskList.size() + 1);
            System.out.println("Added: " + String.join(" ", instructions));
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

    public static void chat() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println(hl);
            System.out.print("> ");
            String userInput = br.readLine();
            String[] instructions = userInput.split(" ", 2);
            if (instructions[0].equals("bye")) {
                bye();
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
        }
    }

    public static void main(String[] args) throws IOException {
        greetings();

        chat();
    }
}
