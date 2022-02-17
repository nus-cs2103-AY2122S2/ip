package mnsky.core;

import mnsky.exceptions.MnskyException;
import mnsky.task.Task;

public class Ui {
    /**
     * Prints the greeting message for the chatbot.
     */
    public String printGreeting() {
        return "Hi, I'm MNSKY.";
    }

    /**
     * Returns the bye message.
     * @return The bye message.
     */
    public String printBye() {
        return "[MNSKY has shut itself down]";
    }

    /**
     * Returns the message for when an exception occurs.
     * @param e The exception to be printed out.
     * @return The message for when an exception occurs.
     */
    public String printException(MnskyException e) {
        return "..? " + e.getMessage();
    }

    /**
     * Returns the string representation of the task.
     * @param task The task to be printed out.
     * @return The string representation of the task.
     */
    public String printTask(Task task) {
        return task.toString();
    }

    /**
     * Returns the message for adding a new task to the list.
     * @param task The new task that was added.
     * @return The message for adding a new task to the list.
     */
    public String printAddedTask(Task task) {
        return String.format("[MNSKY added task %s to their list]", task.getName());
    }

    /**
     * Returns the message for deleting a task.
     * @param task The task that was deleted.
     * @return The message for deleting a task.
     */
    public String printDeletedTask(Task task) {
        return String.format("[MNSKY deleted the task %s from the list.]", task.getName());
    }
}
