package duke.util;

import duke.exception.DukeException;
import duke.exception.ErrorMessage;
import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

// contains the task list e.g., it has operations to add/delete tasks in the list
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public int getSize() {
        return tasks.size();
    }

    public Task getTask(int index) throws DukeException {
        int zeroBasedIndex = index - 1;
        if (zeroBasedIndex < 0 || zeroBasedIndex >= tasks.size()) {
            throw new DukeException(ErrorMessage.MESSAGE_INVALID_INDEX);
        }
        return tasks.get(zeroBasedIndex);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) throws DukeException {
        int zeroBasedIndex = index - 1;
        if (zeroBasedIndex < 0 || zeroBasedIndex >= tasks.size()) {
            throw new DukeException(ErrorMessage.MESSAGE_INVALID_INDEX);
        }
        tasks.remove(zeroBasedIndex);
    }

    public void markAsDone(int index) throws DukeException {
        getTask(index).markAsDone();
    }

    public void unmarkAsDone(int index) throws DukeException {
        getTask(index).unmarkAsDone();
    }

    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }
}
