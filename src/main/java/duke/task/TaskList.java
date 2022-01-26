package duke.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.DukeException;

public class TaskList {
    // Main data structure to store duke.task.Task objects
    private List<Task> tasks;

    public TaskList() {
        this(new ArrayList<>());
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a duke.task.Task to existing duke.task.TaskList.
     *
     * @param task duke.task to add
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes a duke.task.Task from the existing list.
     *
     * @param index index of duke.task.Task to remove
     * @return the removed duke.task.Task
     * @throws DukeException index out of range
     */
    public Task removeTask(int index) throws DukeException {
        checkIndex(index);
        return this.tasks.remove(index);
    }

    /**
     * Checks if index is in range of current List
     *
     * @param index index to check
     * @throws DukeException index out of range
     */
    private void checkIndex(int index) throws DukeException {
        if (index < 0 || index >= this.size()) {
            throw new DukeException(
                    this.size() == 0
                            ? "List is empty"
                            : String.format("Please choose tasks from 1 to %d", this.size())
            );
        }
    }

    /**
     * Marks the duke.task.Task at specified index (marked as done)
     *
     * @param index index of duke.task.Task to mark
     * @return true if the state of duke.task.Task was changed by marking (not done -> done)
     * @throws DukeException index out of range
     */
    public Boolean mark(int index) throws DukeException {
        checkIndex(index);
        Task target = this.tasks.get(index);
        return target.mark();
    }

    /**
     * Unmarks the duke.task.Task (marked as not done)
     *
     * @param index index of duke.task.Task to unmark
     * @return true if the state of duke.task.Task was changed by unmarking (done -> not done)
     * @throws DukeException index out of range
     */
    public Boolean unmark(int index) throws DukeException {
        checkIndex(index);
        Task target = this.tasks.get(index);
        return target.unmark();
    }

    /**
     * Retrieves duke.task.Task at index
     *
     * @param index index of duke.task.Task to get
     * @return resulting duke.task.Task
     * @throws DukeException index out of range
     */
    public Task getTask(int index) throws DukeException {
        checkIndex(index);
        return this.tasks.get(index);
    }

    /**
     *
     * @return size of duke.task.TaskList
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Returns a string, where each line is a duke.task.Task formatted for saving to file
     *
     * @return formatted string of Tasks for saving to file
     */
    public String tasksFileSaveFormat() {
        String result = "";

        for (int i = 0; i < this.size(); i++) {
            try {
                result += this.getTask(i).fileSaveFormat();
            } catch (DukeException e) {
                // This won't happen, given the bounds of the for-loop
                assert false;
            }

            if (i != this.size() - 1) result += "\n";
        }

        return result;
    }

    /**
     * Filter TaskList for Tasks whose names contain the search string
     *
     * @param search substring to search for
     * @return filtered TaskList
     */
    public TaskList searchTasks(String search) {
        return new TaskList(
                this.tasks
                        .stream()
                        .filter(task -> task.getName().contains(search))
                        .collect(Collectors.toList()));
    }

    @Override
    public String toString() {
        if (this.size() == 0) {
            return "[ list is empty ]";
        }

        String result = "";

        for (int i = 0; i < this.size(); i++) {
            try {
                Task current = this.getTask(i);
                result += String.format(
                        "%3d. %s",
                        i + 1,
                        current.nameWithStatus());
            } catch (DukeException e) {
                // This won't happen, given the bounds of the for-loop
                assert false;
            }

            if (i != this.size() - 1) result += "\n";
        }

        return result;
    }
}
