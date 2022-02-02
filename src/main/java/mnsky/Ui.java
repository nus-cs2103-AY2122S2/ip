package mnsky;

import java.util.ArrayList;

import mnsky.exceptions.MnskyException;
import mnsky.task.Task;

public class Ui {
    /**
     * Prints the greeting message for the chatbot.
     */
    public String printGreeting() {
        return "Hi, I'm MNSKY. I can help!";
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
        return "..?\n" + e.getMessage();
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
        return String.format("[MNSKY added task %s to their list]\n", task.getName());
    }

    /**
     * Returns the message for deleting a task.
     * @param task The task that was deleted.
     * @return The message for deleting a task.
     */
    public String printDeletedTask(Task task) {
        return String.format("[MNSKY deleted the task %s from the list.]\n", task.getName());
    }

    /**
     * Combines groups of 5 strings in the list of strings into one string, then returns these strings.
     * @param listStrings The list of strings.
     * @return The list of strings after combining groups of 5 strings into one string.
     */
    public ArrayList<String> printListStrings(ArrayList<String> listStrings) {
        ArrayList<String> newListStrings = new ArrayList<>();
        for (int i = 0; i < listStrings.size(); i += 5) {
            StringBuilder sb = new StringBuilder();
            for (int j = i; j < listStrings.size() && j < i + 5; j++) {
                sb.append(listStrings.get(j));
                sb.append("\n");
            }
            newListStrings.add(sb.toString());
        }

        return newListStrings;
    }
}
