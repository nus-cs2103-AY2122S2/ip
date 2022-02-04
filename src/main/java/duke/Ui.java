package duke;

import java.util.List;

import duke.task.Task;

/**
 * An instance of UI.
 */
public class Ui {
    /**
     * Padding string used to format the bot's responses
     */
    public static final String STR_PADDING = "    ";

    /**
     * Close string.
     *
     * @return A string to acknowledge the program is closing.
     */
    public String close() {
        return "Pleasure to be of service, see you soon! Shutting down in 3 seconds.";
    }

    /**
     * Display the contents of TaskList.
     *
     * @param tasks the TaskList
     * @return the contents of TaskList in string format.
     */
    public String showList(TaskList tasks) {
        StringBuilder sb = new StringBuilder(Ui.STR_PADDING + "Here are the tasks in your list: \n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(String.format(Ui.STR_PADDING + "  %d. " + tasks.get(i), i + 1));
            if (i != tasks.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Shows whether the keyword has been found in the list of Task.
     *
     * @param findString the keyword that the user wants to find
     * @param filteredTask the taskList that has been filtered to search for the keyword
     * @return the contents of filteredTask that matches the keyword, if any.
     */
    public String showFind(String findString, List<Task> filteredTask) {
        if (filteredTask.isEmpty()) {
            return String.format("I could not find any tasks that contains \"%s\"", findString);
        } else {
            StringBuilder sb = new StringBuilder(Ui.STR_PADDING + "You've searched for: \"" + findString + "\"\n");
            sb.append(Ui.STR_PADDING + "Here are the matching tasks in your list:");

            for (int i = 0; i < filteredTask.size(); i++) {
                sb.append(String.format("\n" + Ui.STR_PADDING + "  %d. " + filteredTask.get(i), i + 1));
            }
            return sb.toString();
        }
    }
    /**
     * Show the input task getting marked or unmarked based on its status.
     *
     * @param task the task
     * @return the string
     */
    public String showToggleTask(Task task) {
        StringBuilder sb = new StringBuilder();
        String markedString = "Nice! I've marked this task: ";
        String unmarkedString = "OK, I've unmarked this task: ";
        String outString = task.getIsMarked() ? markedString : unmarkedString;

        sb.append(STR_PADDING).append(outString).append("\n");
        sb.append(STR_PADDING).append("   ").append(task);
        return sb.toString();
    }

    /**
     * Show that the input task has been removed from the list.
     *
     * @param listSize the list size
     * @param task     the task
     * @return A string to acknowledge that a task has been removed from the list.
     */
    public String showDeletion(int listSize, Task task) {
        StringBuilder sb = new StringBuilder();
        String ack = "Noted, I've removed the following task: ";
        String taskString = (listSize == 1) ? "task" : "tasks";
        String size = String.format("Now you have %d %s in the list", listSize, taskString);

        sb.append(STR_PADDING).append(ack).append("\n");;
        sb.append(STR_PADDING).append("   ").append(task).append("\n");
        sb.append(STR_PADDING).append(size);

        return sb.toString();
    }

    /**
     * Show that the input task has been added to the list.
     *
     * @param listSize the list size
     * @param task     the task
     * @return A string to acknowledge that a task has been added to the list.
     */
    public String showAddition(int listSize, Task task) {
        StringBuilder sb = new StringBuilder();
        String taskString = (listSize == 1) ? "task" : "tasks";
        String ack = "Got it, I've added this task: ";
        String size = String.format("Now you have %d %s in the list", listSize, taskString);

        sb.append(STR_PADDING).append(ack).append("\n");
        sb.append(STR_PADDING).append("   ").append(task).append("\n");
        sb.append(STR_PADDING).append(size);

        return sb.toString();
    }

    /**
     * Show an error message containing the input parameter.
     *
     * @param errorMessage the error message
     * @return the error message in a specific format.
     */
    public String showError(String errorMessage) {
        return String.format("Error: %s", errorMessage);
    }
}
