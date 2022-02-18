package myboss;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user interface.
 */
public class Ui {
    static final String INDENT = "    ";

    static final String UNKNOWN_COMMAND_EXCEPTION_MSG = "OOPS!!! Someone typed something wrong!";
    static final String MISSING_TIME_ARGUMENT_EXCEPTION_MSG = "Argument after missing /at or /by!!!";
    static final String MISSING_ARGUMENT_EXCEPTION_MSG = "You're missing some arguments!";
    static final String WRONG_DATE_FORMAT_EXCEPTION_MSG = "Date must be of format yyyy-mm-dd! (/ﾟДﾟ)/";
    static final String INDEX_OUT_OF_BOUNDS_EXCEPTION_MSG = "Index out of bounds!";
    static final String APPEND_TO_FILE_EXCEPTION_MSG = "Error appending task to text file!";
    static final String FILE_NOT_FOUND_EXCEPTION_MSG = "Error file not found!";
    static final String FILE_CREATION_EXCEPTION_MSG = "An Error has occurred with file creation!";
    static final String CLEAR_FILE_EXCEPTION_MSG = "Error clearing File";

    /**
     * Outputs the given task list.
     *
     * @param taskList the specified list of tasks.
     * @return returns the string representation of task list.
     */
    public String outputTaskList(ArrayList<Task> taskList) {
        StringBuilder tempOut = new StringBuilder("");
        tempOut.append("Here are the tasks you should be doing:\n");
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
    public String outputFoundTasks(List<Task> taskList) {
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
                .append("Now you have " + TaskList.getSize() + " tasks in the list.");
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
                .append("Now you have " + TaskList.getSize() + " tasks in the list.");
        return output.toString();
    }

    String outputChangeTaskPriority(Task task) {
        StringBuilder output = new StringBuilder();
        output.append("Noted. I've changed the priority of this task:\n")
                .append(INDENT + "   " + task)
                .append("Now you have " + TaskList.getSize() + " tasks in the list.");
        return output.toString();
    }
}
