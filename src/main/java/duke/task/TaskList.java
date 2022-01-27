package duke.task;

import java.util.ArrayList;

/**
 * Responsible for adding and removing tasks from the list and performing operations on them.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs a new TaskList object with an initial list of tasks
     * @param tasks The initial list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs a new TaskList object with no preexisting tasks.
     */
    public TaskList() {
        this(new ArrayList<>());
    }

    /**
     * Returns the ArrayList of all tasks in the TaskList.
     * @return tasks, the ArrayList of tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns a task by the index corresponding to its position in a numbered list.
     * @param index The index of the task selected
     * @return The selected task
     * @throws TaskOutOfBoundsException If there is no task with the given index
     */
    public Task getTask(int index) throws TaskOutOfBoundsException {
        if (0 < index & index <= tasks.size()) {
            return tasks.get(index - 1);
        } else {
            throw new TaskOutOfBoundsException("No task number " + index);
        }
    }

    /**
     * Deletes a task by the index corresponding to its position in a numbered list.
     * @param index The index of the task selected
     * @return The deleted task
     * @throws TaskOutOfBoundsException If there is no task with the given index
     */
    public Task deleteTask(int index) throws TaskOutOfBoundsException {
        if (0 < index & index <= tasks.size()) {
            return tasks.remove(index - 1);
        } else {
            throw new TaskOutOfBoundsException("No task number " + index);
        }
    }

    /**
     * Adds the given task to tasks.
     * @param task The task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }
}