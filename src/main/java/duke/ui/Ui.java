package duke.ui;

import java.util.Scanner;

import duke.task.Task;
import duke.util.DukeException;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * Represents the user interface.
 */
public class Ui {
    private final Scanner sc;
    /**
     * Constructs a user interface object.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Appends an 's' to the back of a word if it is plural.
     */
    private static String pluralise(int n) {
        if (n == 1) {
            return " ";
        }
        assert n != 1 : "n should not be 1";
        return "s ";
    }

    /**
     * Displays welcome message.
     *
     * @params isEmpty Whether there are saved tasks.
     * @return Welcome message.
     */
    public String welcome(TaskList tasks) {
        String greeting = "Hello! I'm Duke.";
        if (tasks.isEmpty()) {
            return greeting + " What can I do for you?";
        }
        assert !tasks.isEmpty() : "There should be saved tasks";
        return greeting + "\n" + listTasks(tasks);
    }

    /**
     * Displays tasks.
     * @param tasks Tasks.
     */
    public String listTasks(TaskList tasks) {
        if (tasks.isEmpty()) {
            return "You have no tasks in your list.";
        }
        assert !tasks.isEmpty() : "There should be at least one task";
        return "Here are the tasks in your list:\n" + tasks;
    }

    /**
     * Displays tasks that match the keyword.
     * @param tasks Tasks the match the keyword.
     */
    public String filterTasks(TaskList tasks) {
        return "Here are the matching tasks in your list:\n" + tasks;
    }

    /**
     * Displays number of tasks.
     * @param tasks Tasks.
     */
    public String countTasks(TaskList tasks) {
        return "You now have " + tasks.size() + " task" + pluralise(tasks.size()) + "in the list.";
    }

    /**
     * Displays task added.
     * @param task Task added.
     */
    public String addTask(Task task) {
        return "Got it. I've added this task:\n" + task;
    }

    /**
     * Displays marked task.
     * @param task Marked task.
     */
    public String markTask(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Displays unmarked task.
     * @param task Unmarked task.
     */
    public String unmarkTask(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task;
    }

    /**
     * Displays deleted task.
     * @param task Deleted task.
     */
    public String deleteTask(Task task) {
        return "Noted. I've removed this task:\n" + task;
    }

    /**
     * Saves tasks to storage and displays farewell message.
     * @param storage Storage.
     * @param tasks Tasks to be saved.
     */
    public String exit(Storage storage, TaskList tasks) throws DukeException {
        sc.close();
        storage.save(tasks);
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * Reads incoming command.
     * @return Incoming command.
     */
    public String readCommand() {
        return sc.nextLine();
    }
}
