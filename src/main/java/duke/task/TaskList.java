package duke.task;

import duke.DukeException;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void markTask(int taskId, boolean isDone) throws DukeException {
        try {
            if (isDone) {
                tasks.get(taskId).markAsDone();
            } else {
                tasks.get(taskId).markAsNotDone();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(e.getMessage());
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int taskId) throws DukeException {
        try {
            tasks.remove(taskId);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(e.getMessage());
        }
    }

    public Task getTask(int taskId) throws DukeException {
        try {
            return this.tasks.get(taskId);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(e.getMessage());
        }
    }

    public int getSize() {
        return tasks.size();
    }
}
