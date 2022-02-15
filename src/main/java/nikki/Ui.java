package nikki;

import nikki.task.Task;
import nikki.task.TaskList;

public class Ui {
    /**
     * Logs the list of Tasks in TaskList's string representation format
     *
     * @param tasks TaskList to print
     * @return formatted string
     */
    public String getListReport(TaskList tasks) {
        return String.format(
                "Total %d found:\n"
                + tasks.toString(),
                tasks.size());
    }

    /**
     * Logs the number of tasks in list
     *
     * @param taskCount task count to print
     * @return formatted string
     */
    public String getTaskCountReport(int taskCount) {
        return String.format("Now you have %d tasks in the list", taskCount);
    }

    /**
     * Logs the addition of tasks in the same format
     *
     * @param task task added to be logged
     * @return formatted string
     */
    public String getNewTaskResponse(Task task) {
        return String.format(
                "Added following task:\n"
                + "\t%s\n",
                task.nameWithStatus());
    }

    /**
     * Logs the deletion of task from the list
     *
     * @param task deleted Task
     * @return formatted string
     */
    public String getDeletedTaskResponse(Task task) {
        return String.format(
                "Removed this task:\n"
                + "\t%s\n",
                task.nameWithStatus());
    }

    /**
     * Logs the updating of task from the list
     *
     * @param task updated Task
     * @return formatted string
     */
    public String getUpdateTaskResponse(Task task) {
        return "Updated the following task\n"
                + "\t" + task.nameWithStatus();
    }

    /**
     * Logs the clearing of tasks from the list
     *
     * @return formatted String
     */
    public String getClearedListResponse() {
        return "Task list cleared";
    }
}
