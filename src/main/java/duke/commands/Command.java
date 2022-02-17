package duke.commands;

import duke.exceptions.DukeException;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public abstract class Command {

    public abstract String execute(TaskList tasks, Ui ui) throws DukeException;

    /**
     * Default method to check if command should exit.
     * Should always return false for most commands except the exit command.
     *
     * @return Always false unless overridden by specific command implementation.
     */
    public boolean isExit() {
        return false;
    }
}
