package user;

import java.util.ArrayList;

import exceptions.DukeException;
import tasks.Task;
import tasks.Tasklist;

/** A class that handles the interactions with the user. */
public class Ui {

    private static final String[] HELP_MESSAGE = new String[] {
        "Commands:",
        "'todo [some activity]' - add a todo",
        "'deadline [some task] /by [dd/mm/yyyy-hh:mm]' - add a deadline",
        "'event [some event] /at [dd/mm/yyyy-hh:mm]' - add an event",
        "'lend [some amount] /to [name]' - add an amount of money to be collected",
        "'borrow [some amount] /from [name]' - add an amount of money to pay",
        "'list'- view all tasks",
        "'find [keyword]' - search tasks by keyword",
        "'mark [task number]' - mark a task as done",
        "'unmark [task number]' - mark a task as not done",
        "'delete [task number]' - delete a task"
    };

    private final Tasklist tasklist;
    private final Parser parser;

    private boolean wasError = false;

    /**
     * Constructor method to create a new tasklist and parser.
     */
    public Ui() {
        tasklist = new Tasklist();
        tasklist.loadTasks();
        parser = new Parser();
    }

    /**
     * Displays the tasks saved to the user.
     *
     * @return An array of Strings.
     */
    public String[] displayTasks() {
        String[] result;
        if (tasklist.getNumTasks() == 0) {
            result = new String[] {"You have no tasks."};
        } else {
            result = new String[1 + tasklist.getNumTasks()];
            result[0] = "Here are the tasks in your list:";
            for (int i = 0; i < tasklist.getNumTasks(); i++) {
                result[i + 1] = String.format("%d. %s", i + 1, tasklist.getTask(i));
            }
        }
        return result;
    }

    /**
     * Displays each task in an ArrayList of tasks.
     *
     * @return An array of Strings.
     */
    public String[] displayFoundTasks(ArrayList<Task> foundTasks) {
        String[] result;
        if (foundTasks.size() == 0) {
            result = new String[] {"No tasks are found."};
        } else {
            result = new String[1 + foundTasks.size()];
            result[0] = "Here are the matching tasks in your list:";
            for (int i = 0; i < foundTasks.size(); i++) {
                result[i + 1] = String.format("%d. %s", i + 1, foundTasks.get(i));
            }
        }
        return result;
    }


    /**
     * Handles the user's input, and performs the corresponding action.
     *
     * @param userInput The user's input
     * @return An array of Strings to be printed.
     */

    public String[] handle(String userInput) {
        wasError = false;
        String[] result = {};
        try {
            if (userInput.equals("help")) { // help
                result = HELP_MESSAGE;
            } else if (userInput.equals("list")) { // display tasks
                result = displayTasks();
            } else if (userInput.startsWith("mark ")) { // mark task as done
                int taskToMark = parser.handleMarkTask(userInput, tasklist.getNumTasks());
                result = tasklist.markTask(taskToMark);
            } else if (userInput.startsWith("unmark ")) { // mark task as undone
                int taskToUnmark = parser.handleUnmarkTask(userInput, tasklist.getNumTasks());
                result = tasklist.unmarkTask(taskToUnmark);
            } else if (userInput.startsWith("delete ")) { // delete task
                int taskToDelete = parser.handleDeleteTask(userInput, tasklist.getNumTasks());
                result = tasklist.deleteTask(taskToDelete);
            } else if (userInput.startsWith("find ")) { // find task
                String keywords = parser.handleFindTask(userInput);
                ArrayList<Task> foundTasks = tasklist.findTasks(keywords);
                result = displayFoundTasks(foundTasks);
            } else { // add task
                Task t = parser.addTask(userInput);
                tasklist.addTask(t);
                result = new String[] {
                    "Got it. I've added this task:",
                    t.toString(),
                    "Now you have " + tasklist.getNumTasks() + " tasks in the list."
                };
            }
            tasklist.saveTasks();
        } catch (DukeException err) {
            wasError = true;
            result = new String[] {err.getMessage()};
        }
        return result;
    }

    /**
     * Getter for wasError field.
     * @return true if the previous input led to an error.
     */
    public boolean handledError() {
        return this.wasError;
    }
}
