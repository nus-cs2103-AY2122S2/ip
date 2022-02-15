package duke;
import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles interactions with the user through input and output.
 */
public class Ui {

    /**
     * Ui constructor.
     */
    public Ui() {
    }

    /**
     * Greets the user as the app starts up.
     */
    public String greet() {
        return "Hello! I'm Duke\n" + "What do you need me to note down for you? Type it below!\n" +
                "Feel free to identify the status of your tasks by entering 'marked' or 'unmarked' along with the " +
                "task number!";
    }

    /**
     * Sends goodbye to the user as the app shuts down.
     */
    public String goodbye() {
        return "Bye. Have a great day!";
    }

    /**
     * Prints out a given list of tasks for the user to see.
     * @param tasks arraylist of desired tasks to show the user.
     */
    public String printTasks(ArrayList<Task> tasks) {
        StringBuilder taskList = new StringBuilder();
        taskList.append("Here are the requested tasks:\n");
        for (int i = 1; i <= tasks.size(); i++) {
            taskList.append(i).append(". ").append(tasks.get(i - 1)).append("\n");
        }
        String out = taskList.toString().trim();
        //output cannot be empty
        assert !out.equals("");
        return(out);
    }

    /**
     * Returns a message when a task has been successfully deleted.
     * @param t task that was removed.
     */
    public String notifyRemovedTaskMessage(Task t) {
        return "Noted. I've removed this task:\n" + t;
    }

    /**
     * Returns a message when a task has been successfully added.
     * @param t task that was added.
     */
    public String notifyAddedTaskMessage(Task t) {
        return "Noted. I've added this task:\n" + t;
    }

    /**
     * Returns a message when a task was successfully marked as incomplete or complete.
     * @param t task that was marked
     * @param isComplete boolean flag for whether the task was marked complete or not complete.
     */
    public String notifyMarkedTaskMessage(Task t, boolean isComplete) {
        if (isComplete) {
            return "Task:\n" + t + "\nhas been marked complete.";
        } else {
            return "Task:\n" + t + "\nhas been marked incomplete.";
        }
    }

    /**
     * Returns a message to notify the user when tasks have been successfully archived
     */
    public String notifyArchivedTasks() {
        return "All completed tasks have been moved to the archive.";

    }

}