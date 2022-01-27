package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

public abstract class Command {
    protected String key;

    Command(String key) {
        this.key = key;
    }

    public abstract void execute(String input, TaskList taskList, Storage storage, Ui ui) throws DukeException;

    protected String getTaskDescription(String input, String emptyDescErrDesc) throws DukeException {
        String taskDescription = input.substring(input.indexOf(key) + key.length() + 1);

        if (taskDescription.length() == 0) {
            throw new DukeException(emptyDescErrDesc);
        }

        return taskDescription;
    }

    public String getKey() {
        return key;
    }
}
