package seedu.storage;

import seedu.task.Task;
import seedu.duke.DukeException;

import java.util.ArrayList;

public class Storage {

    public Storage(String filePath) {

    }

    public ArrayList<Task> load() throws DukeException {
        return new ArrayList<Task>();
    }
}
