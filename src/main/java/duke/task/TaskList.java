package duke.task;

import duke.DukeException;

import java.util.ArrayList;
import java.util.List;

public class TaskList extends ArrayList<Task> {

    public void mark(int id) {
        if (id >= size()) {
            throw new DukeException("Invalid id");
        }

        get(id).setDone(true);
    }

    public void unmark(int id) {
        if (id >= size()) {
            throw new DukeException("Invalid id");
        }

        get(id).setDone(false);
    }

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
