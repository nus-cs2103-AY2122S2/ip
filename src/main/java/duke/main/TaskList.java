package duke.main;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void markTask(int zeroBasedIndex, boolean isDone) throws DukeException {
        try {
            tasks.get(zeroBasedIndex).mark(isDone);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(DukeException.ERROR_INVALID_INDEX);
        }
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void delete(int zeroBasedIndex) throws DukeException {
        try {
            tasks.remove(zeroBasedIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(DukeException.ERROR_INVALID_INDEX);
        }
    }

    public Task getTask(int zeroBasedIndex) throws DukeException {
        try {
            return this.tasks.get(zeroBasedIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(DukeException.ERROR_INVALID_INDEX);
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getSize() {
        return tasks.size();
    }
}
