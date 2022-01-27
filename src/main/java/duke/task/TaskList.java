package duke.task;

import duke.exception.DukeException;

import java.util.ArrayList;

/**
 * Contains the task list and basic operations involving it.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    // tasklist exception messages
    private final static String INDEX_NO_EXIST = "Yo yo yo, this task don't exist. Give a legitimate task number.";

    /**
     * Default constructor for TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    /**
     * Marks or unmarks a task based on its tasklist index.
     *
     * @param taskIndex Index of task that is to be marked/unmarked.
     * @param isMark Mark or unmark task.
     * @return Task that was upadated.
     * @throws DukeException If index of task does not exist.
     */
    public Task markTask(int taskIndex, boolean isMark) throws DukeException {
        if (taskIndex >= tasks.size()) {
            throw new DukeException(INDEX_NO_EXIST);
        }

        Task task = tasks.get(taskIndex);
        task.setIsDone(isMark);

        return task;
    }

    /**
     * Adds new task.
     *
     * @param newTask New task to be added into the Task list.
     */
    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    /**
     * Remove a task from tasklist.
     *
     * @param taskIndex The index of the task that is to be deleted from the list.
     * @return Return the task that was removed from the tasklist.
     * @throws DukeException If task does not exist.
     */
    public Task removeTask(int taskIndex) throws DukeException {
        if (taskIndex >= tasks.size()) {
            throw new DukeException(INDEX_NO_EXIST);
        }

        Task task = tasks.get(taskIndex);
        tasks.remove(taskIndex);

        return task;
    }

    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    public int getTaskListSize() {
        return tasks.size();
    }

    /**
     * Gets the task list in string format.
     *
     * @return Returns the tasklist in a certain format that lists and prints out each task.
     */
    public String getTaskListStr() {
        StringBuilder sb = new StringBuilder("");

        for (int i = 0; i < tasks.size(); ++i) {
            sb.append(String.valueOf(i + 1)).append(". ").append(tasks.get(i).toString()).append("\n");
        }

        return sb.toString();
    }
}
