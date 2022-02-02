package duke;

import duke.task.Task;
import duke.task.TaskList;

import java.util.List;

/**
 * Ui class which prints all the user interface
 */
public class Ui {
    private static final String LINES = "____________________________________________________________";

    /**
     * Default constructor for Ui class
     */
    Ui() {
    }

    /**
     * Shows the introduction to Duke
     */
    public void showWelcome() {
        System.out.println(LINES);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(LINES);
    }

    /**
     * Shows the goodbye message
     */
    public void sayGoodbye() {
        System.out.println(LINES + "\nBye. Hope to see you again soon!\n" + LINES);
    }

    /**
     * Prints the message passed into the function wrapped between lines
     *
     * @param s Message to print
     */
    public void sayMessage(String s) {
        System.out.println(LINES + "\n" + s + "\n" + LINES);
    }

    /**
     * Prints all the tasks passed into the function
     *
     * @param tasks List of tasks to print
     */
    public void listTasks(TaskList tasks) {
        System.out.println(LINES);
        System.out.println("Here are the tasks in your list:");
        List<Task> list = tasks.getTasks();
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + "." + list.get(i).toString());
        }
        System.out.println(LINES);
    }

    /**
     * Overloaded listTasks function that takes in an ArrayList instead
     *
     * @param tasks List of tasks to print
     */
    public void listTasks(List<Task> tasks) {
        System.out.println(LINES);
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }
        System.out.println(LINES);
    }

    /**
     * Lists all tasks in a string format
     *
     * @param tasks tasks to list
     * @return All tasks in a string format
     */
    public String listTasksAsString(TaskList tasks) {
        String response = "Here are the tasks in your list:\n";
        List<Task> list = tasks.getTasks();
        for (int i = 0; i < list.size(); i++) {
            response += ((i + 1) + "." + list.get(i).toString() + "\n");
        }
        return response;
    }

    /**
     * Overloaded listTasksAsString function that takes in an ArrayList
     *
     * @param tasks tasks to list
     * @return All tasks in a string format
     */
    public String listTasksAsString(List<Task> tasks) {
        String response = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            response += ((i + 1) + "." + tasks.get(i).toString() + "\n");
        }
        return response;
    }
}
