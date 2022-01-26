package duke.task;

import java.util.ArrayList;
import java.util.List;

import duke.DukeException;

public class TaskList {
    /**
     * Main data structure to store Task objects
     */
    private List<Task> tasks = new ArrayList<>();

    /**
     * Adds a Task to existing TaskList.
     *
     * @param task Task to add
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes a Task from the existing list.
     *
     * @param index index of Task to remove
     * @return the removed Task
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
     * Marks the Task at specified index (marked as done)
     *
     * @param index index of Task to mark
     * @return true if the state of Task was changed by marking (not done -> done)
     * @throws DukeException index out of range
     */
    public Boolean mark(int index) throws DukeException {
        checkIndex(index);
        Task target = this.tasks.get(index);
        return target.mark();
    }

    /**
     * Unmarks the Task (marked as not done)
     *
     * @param index index of Task to unmark
     * @return true if the state of Task was changed by unmarking (done -> not done)
     * @throws DukeException index out of range
     */
    public Boolean unmark(int index) throws DukeException {
        checkIndex(index);
        Task target = this.tasks.get(index);
        return target.unmark();
    }

    /**
     * Retrieves Task at index
     *
     * @param index index of Task to get
     * @return resulting Task
     * @throws DukeException index out of range
     */
    public Task getTask(int index) throws DukeException {
        checkIndex(index);
        return this.tasks.get(index);
    }

    /**
     *
     * @return size of TaskList
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Returns a string, where each line is a Task formatted for saving to file
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
