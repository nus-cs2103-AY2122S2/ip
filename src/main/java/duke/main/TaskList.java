package duke.main;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
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
        return this.tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    public ArrayList<Task> findTasks(String searchDescription) {
        return new ArrayList<Task>(tasks.stream()
                .filter(task -> task.hasSubstring(searchDescription))
                .collect(Collectors.toList()));
    }
}
