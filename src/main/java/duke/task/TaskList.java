package duke.task;

import java.util.ArrayList;

/**
 * Responsible for adding, removing, and performing operations on tasks in taskList.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this(new ArrayList<>());
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task getTask(int index) throws TaskOutOfBoundsException {
        if (0 < index & index <= tasks.size()) {
            return tasks.get(index - 1);
        } else {
            throw new TaskOutOfBoundsException("No task number " + index);
        }
    }

    public Task deleteTask(int index) throws TaskOutOfBoundsException {
        if (0 < index & index <= tasks.size()) {
            return tasks.remove(index - 1);
        } else {
            throw new TaskOutOfBoundsException("No task number " + index);
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
    }
}