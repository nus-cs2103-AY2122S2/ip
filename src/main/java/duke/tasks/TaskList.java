package duke.tasks;

import java.util.ArrayList;

/**
 * ArrayList of Task objects.
 */
public class TaskList {

    private final ArrayList<Task> taskList;

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Get task at given index.
     *
     * @param index Index of task to get.
     * @return Task at given index.
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Get content of task at given index.
     *
     * @param index Index of task to get content.
     * @return Content of task at given index.
     */
    public String getTaskContent(int index) {
        return taskList.get(index).getContent();
    }

    /**
     * Adds Task to TaskList.
     *
     * @param taskToAdd Task to add to TaskList.
     */
    public void addTask(Task taskToAdd) {
        taskList.add(taskToAdd);
    }

    /**
     * Deletes Task from Tasklist specified by given index.
     *
     * @param indexTaskToDelete Index of Task to delete.
     * @return Deleted task.
     */
    public Task deleteTask(int indexTaskToDelete) {
        return taskList.remove(indexTaskToDelete);
    }

    /**
     * Marks Task from TaskList specified by given index.
     *
     * @param indexTaskToMark Index of Task to mark.
     * @return Marked task
     */
    public Task markTask(int indexTaskToMark) {
        Task taskToMark = taskList.get(indexTaskToMark);
        taskToMark.markAsDone();
        return taskToMark;
    }

    /**
     * Unmarks Task from TaskList specified by given index.
     *
     * @param indexTaskToUnmark Index of Task to Unmark.
     * @return Unmarked task
     */
    public Task unmarkTask(int indexTaskToUnmark) {
        Task taskToUnmark = taskList.get(indexTaskToUnmark);
        taskToUnmark.markAsNotDone();
        return taskToUnmark;
    }

    /**
     * Returns number of tasks in TaskList.
     *
     * @return size of TaskList.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Formats each Task in TaskList to write into save file.
     *
     * @return Data to write into save file.
     */
    public String toSaveData() {
        StringBuilder dataToWrite = new StringBuilder();
        for (Task task : taskList) {
            dataToWrite.append(task.toSaveData());
        }
        return dataToWrite.toString();
    }

    /**
     * Returns string indicating empty TaskList if empty.
     * Else return numbered list of task.
     *
     * @return String representation of TaskList.
     */
    @Override
    public String toString() {
        if (taskList.size() == 0) {
            return "Your current task list is empty";
        }
        StringBuilder numberedTaskList = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            numberedTaskList.append(String.format("%d. %s \n", i + 1, taskList.get(i)));
        }
        return numberedTaskList.toString().trim();
    }
}
