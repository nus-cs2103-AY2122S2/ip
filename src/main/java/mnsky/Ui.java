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
     * Prints out the bye messages.
     */
    public String printBye() {
        return "[MNSKY has shut itself down]";
    }

    /**
     * Prints out the exception messages.
     * @param e The exception to be printed out.
     */
    public String printException(MnskyException e) {
        return "..?\n" + e.getMessage();
    }

    /**
     * Prints out the string representation of the task.
     * @param task The task to be printed out.
     */
    public String printTask(Task task) {
        return task.toString();
    }

    /**
     * Prints out the message for adding a new task to the list.
     * @param task The new task that was added.
     */
    public String printAddedTask(Task task) {
        return String.format("[MNSKY added task %s to their list]\n", task.getName());
    }

    /**
     * Prints out the message for deleting a task to the list.
     * @param task The task that was deleted.
     */
    public String printDeletedTask(Task task) {
        return String.format("[MNSKY deleted the task %s from the list.]\n", task.getName());
    }

    /**
     * Prints out all the strings in th l ist.
     * @param listStrings The list of strings.
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
