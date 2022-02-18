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
     * @param tasks The task list.
     * @return Welcome message.
     */
    public String welcome(TaskList tasks) {
        String greeting = "Hello!\n\nYour trusty task manager Sparrow is here!\nWhat can I do for you?\n\n";
        String help = "For a list of commands, enter 'help'.\n\n";
        return greeting + help + listTasks(tasks);
    }

    /**
     * Prints the list of commands.
     *
     * @return the list of commands.
     */
    public String help() {
        return "Here are the formats of all our commands:\n\n"
                + "Adding tasks:\n"
                + "event DESCRIPTION /at YYYY-MM-DD HHMM\n"
                + "deadline DESCRIPTION /by YYYY-MM-DD HHMM\n"
                + "todo DESCRIPTION\n\n"
                + "Viewing tasks:\n"
                + "list\n"
                + "find KEYWORD\n\n"
                + "Editing tasks:\n"
                + "mark INDEX\n"
                + "unmark INDEX\n"
                + "prioritise INDEX PRIORITY_LEVEL (high, medium, low)\n\n"
                + "Deleting tasks:\n"
                + "delete INDEX\n\n"
                + "Saving tasks:\n"
                + "bye\n\n"
                + "Enjoy!";
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
     * Displays the task whose priority has been changed.
     * @param task The task whose priority has been changed.
     */
    public String prioritiseTask(Task task) {
        return "Got it, I've changed the priority of this task:\n" + task;
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
}
