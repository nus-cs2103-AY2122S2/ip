package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.exceptions.DukeException;
import seedu.duke.task.TaskList;
import seedu.duke.Ui;

import java.io.IOException;

public abstract class Command {
    public abstract TaskList execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException;
    public abstract boolean isExit();
}
