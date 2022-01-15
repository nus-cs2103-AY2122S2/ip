import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;

import java.util.Scanner;

/**
 * Main Chat interface class.
 */
public class Chat {
    TaskManager taskManager = new TaskManager();
    Scanner scanner = new Scanner(System.in);

    /**
     * Starts the chat between Duke and the User.
     */
    public void start() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            String command = getCommandFromInput(input);
            if (command.equals("list")) {
                //print out all the tasks
                System.out.println("Here are the tasks in your list:\n" + taskManager.getPrintableListOfTasks());
            } else if (command.equals("mark")) {
                int indexOfTask = Integer.parseInt(input.substring(5));
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(taskManager.completeTask(indexOfTask));
            } else if (command.equals("unmark")) {
                int indexOfTask = Integer.parseInt(input.substring(7));
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(taskManager.removeCompletedStatusOfTask(indexOfTask));
            } else if (command.equals("todo")) {
                Task task = new Task(input.substring(5));
                taskManager.addTask(task);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + task);
                System.out.println("Now you have " + taskManager.getNumberOfTasks() + " tasks in the list.");
            } else if (command.equals("event")) {
                int indexOfStartDate = input.indexOf(" /") + 5;
                Event task = new Event(input.substring(6, input.indexOf(" /")), input.substring(indexOfStartDate));
                taskManager.addTask(task);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + task);
                System.out.println("Now you have " + taskManager.getNumberOfTasks() + " tasks in the list.");
            } else if (command.equals("deadline")) {
                int indexOfStartDate = input.indexOf(" /") + 5;
                Deadline task = new Deadline(input.substring(9, input.indexOf(" /")), input.substring(indexOfStartDate));
                taskManager.addTask(task);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + task);
                System.out.println("Now you have " + taskManager.getNumberOfTasks() + " tasks in the list.");
            }
            //get next command
            input = scanner.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Extracts the command from a given input.
     * @param input
     * @return the command
     */
    private String getCommandFromInput(String input) {
        int indexOfWhitespace = input.indexOf(" ");
        if (indexOfWhitespace == -1) {
            //1 word input
            return input;
        } else {
            return input.substring(0, indexOfWhitespace);
        }
    }
}
