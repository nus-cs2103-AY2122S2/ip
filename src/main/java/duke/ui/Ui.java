package duke.ui;

import java.util.ArrayList;

import duke.task.Task;


/**
 * Responsible for interacting with the user by receiving input and displaying output.
 */
public class Ui {

    /**
     * Displays an exit message to the user.
     *
     * @return The exit message.
     */
    public String displayGoodbye() {
        return "See you later!";
    }

    /**
     * Returns the user's tasks that match a keyword in a numbered list.
     *
     * @param tasks The ArrayList containing all matching tasks.
     * @return The list of matching tasks.
     */
    public String displayAllTasks(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            return "There are no tasks in your list.";
        } else {
            StringBuilder strBuilder = new StringBuilder();
            for (int i = 0; i < tasks.size(); i++) {
                String line = i + 1 + ". " + tasks.get(i) + '\n';
                strBuilder.append(line);
            }
            return "Here are your tasks:\n" + strBuilder.toString();
        }
    }

    /**
     * Returns the user's tasks that match a keyword in a numbered list.
     *
     * @param tasks The ArrayList containing all matching tasks.
     * @return The list of matching tasks.
     */
    public String displayMatchingTasks(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            return "There are no matching tasks in your list.";
        } else {
            StringBuilder strBuilder = new StringBuilder();
            for (int i = 0; i < tasks.size(); i++) {
                String line = i + 1 + ". " + tasks.get(i) + '\n';
                strBuilder.append(line);
            }
            return "Here are the matching tasks:\n" + strBuilder.toString();
        }
    }

    /**
     * Returns a message saying that the task has been marked as complete.
     *
     * @param task The marked task.
     * @return The marked task message.
     */
    public String displayMarkedTask(Task task) {
        return "The following task has been marked as complete:\n" + task.toString();
    }

    /**
     * Returns a message saying that the task has been marked as incomplete.
     *
     * @param task The unmarked task.
     * @return The unmarked task message.
     */
    public String displayUnmarkedTask(Task task) {
        return "The following task has been marked as incomplete:\n" + task.toString();
    }

    /**
     * Returns a message saying the given task has been deleted.
     *
     * @param task The added task.
     * @return The message saying the task has been deleted.
     */
    public String displayDeletedTask(Task task) {
        return "The following task has been deleted:\n" + task.toString();
    }

    /**
     * Returns a message saying the given task has been added.
     *
     * @param task The added task.
     * @return The message saying the task has been added.
     */
    public String displayAddedTask(Task task) {
        return "The following task has been added:\n" + task.toString();
    }

    /**
     * Returns how many tasks are currently in the user's list.
     *
     * @param tasks The ArrayList of tasks.
     * @return The number of tasks in the list.
     */
    public String displayNumberOfTasks(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            return "You have 0 tasks in your list";
        } else if (tasks.size() == 1) {
            return "You have 1 task in your list";
        } else {
            return "You have " + tasks.size() + " tasks in your list";
        }
    }
}
