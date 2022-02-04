package myboss;

import java.util.Scanner;

import java.util.ArrayList;

/**
 * Represents a user interface.
 */
public class Ui {
    static final String INDENT = "    ";

    /**
     * Outputs the greeting message of the chatbot.
     */
    public String greeting() {
        return " Hello! I'm YourBoss.\n" +
                " What can you do for me?";
    }

    /**
     * Outputs the farewell message of the chatbot.
     */
    public String farewell() {
        return " Bye. Hope I never see you again!";
    }

    /**
     * Outputs the given task list.
     *
     * @param taskList the specified list of tasks.
     * @return returns the string representation of task list.
     */
    public String outputTaskList(ArrayList<Task> taskList) {
        StringBuilder tempOut = new StringBuilder("");
        tempOut.append("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            tempOut.append(INDENT).append((i + 1) + ".").append(taskList.get(i).toString());
        }
        return tempOut.toString();
    }

    /**
     * Output the given list of tasks that have been found with the find command.
     *
     * @param taskList the specified list of tasks.
     * @return string representation of list of found tasks.
     */
    public String outputFoundTasks(ArrayList<Task> taskList) {
        StringBuilder tempOut = new StringBuilder("");
        tempOut.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            tempOut.append(INDENT).append((i + 1) + ".").append(taskList.get(i).toString());
        }
        return tempOut.toString();
    }

    /**
     * Output the given task as marked.
     *
     * @param task the specified task marked as done.
     * @return string output when a task is marked as done.
     */
    public String outputMarked(Task task) {
        return task.markAsDone(true);
    }

    /**
     * Output the given task as unmarked.
     *
     * @param task the specified task marked as not done.
     * @return string output when a task is marked as not done.
     */
    public String outputUnmarked(Task task) {
        return task.markAsDone(false);
    }

    /**
     * Output the predefined output when adding a task.
     *
     * @param task task added.
     * @return string output after adding a new task.
     */
    String addTaskOutput(Task task) {
        StringBuilder output = new StringBuilder();
        output.append("Got it. I've added this task:\n")
                .append(INDENT + "   " + task)
                .append("Now you have "+ TaskList.getSize() +" tasks in the list.");
        return output.toString();
    }

    /**
     * Output the predefined output when deleting a task.
     *
     * @param task task deleted.
     * @return string output after deleting a task.
     */
    String outputDeleteTask(Task task) {
        StringBuilder output = new StringBuilder();
        output.append("Noted. I've removed this task:\n")
                .append(INDENT + "   " + task)
                .append("Now you have "+ TaskList.getSize() +" tasks in the list.");
        return output.toString();
    }
}
