package duke.task;

import java.util.ArrayList;
import java.util.List;

import duke.DukeException;

/**
 * Represents a list of Tasks.
 */
public class TaskList extends ArrayList<Task> {

    /**
     * Marks the Task at a specified index as done.
     *
     * @param id The index of the Task to be marked.
     */
    public void mark(int id) {
        if (id >= size()) {
            throw new DukeException("Invalid id");
        }

        get(id).setDone(true);
    }

    /**
     * Marks the Task at a specified index as not done.
     *
     * @param id The index of the Task to be unmarked.
     */
    public void unmark(int id) {
        if (id >= size()) {
            throw new DukeException("Invalid id");
        }

        get(id).setDone(false);
    }

    /**
     * Searches for Tasks that have a description containing a keyword.
     *
     * @param keyword The keyword to search for.
     * @return The Tasks with descriptions containing the keyword.
     */
    public List<Task> search(String keyword) {
        TaskList matchingTasks = new TaskList();

        for (Task task : this) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        return matchingTasks;
    }
}
