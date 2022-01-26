package duke.task;

import duke.DukeException;

import java.util.ArrayList;

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
}
