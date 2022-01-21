import java.util.ArrayList;
import java.util.List;

public class TaskList {
    // Main data structure to store Task objects
    private List<Task> list = new ArrayList<>();

    /**
     * Add a Task to existing TaskList.
     *
     * @param task Task to add
     */
    public void addTask(Task task) {
        this.list.add(task);
    }

    /**
     * Checks if index is in range of current List
     *
     * @param index Index to check
     * @throws DukeException Index out of range
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
     * @param index Index of Task to mark
     * @return True if the state of Task was changed by marking (not done -> done)
     * @throws DukeException Index out of range
     */
    public Boolean mark(int index) throws DukeException {
        checkIndex(index);
        Task target = this.list.get(index);
        return target.mark();
    }

    /**
     * Unmarks the Task (marked as not done)
     *
     * @param index Index of Task to unmark
     * @return True if the state of Task was changed by unmarking (done -> not done)
     * @throws DukeException Index out of range
     */
    public Boolean unmark(int index) throws DukeException {
        checkIndex(index);
        Task target = this.list.get(index);
        return target.unmark();
    }

    /**
     * Retrieves Task at index
     *
     * @param index Index of Task to get
     * @return Resulting Task
     * @throws DukeException Index out of range
     */
    public Task getTask(int index) throws DukeException {
        checkIndex(index);
        return this.list.get(index);
    }

    /**
     *
     * @return Size of TaskList
     */
    public int size() {
        return this.list.size();
    }

    @Override
    public String toString() {
        if (this.size() == 0) {
            return "[ list is empty ]";
        }

        String result = "";

        for (int task_idx = 0; task_idx < this.size(); task_idx++) {
            try {
                Task current = this.getTask(task_idx);
                result += String.format(
                        "%3d. %s",
                        task_idx + 1,
                        current.nameWithStatus());
            } catch (DukeException e) {
                // This won't happen
            }

            if (task_idx != this.size() - 1) result += "\n";
        }

        return result;
    }
}
