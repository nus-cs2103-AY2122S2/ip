package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * This Command class is the parent class for all commands to be executed.
 */
public abstract class Command {

    protected final String commandWord;
    protected final String commandExample;

    protected Command(String action, String example) {
        commandWord = action;
        commandExample = example;
    }

    /**
     * Executes command by printing exit message.
     *
     * @param taskList  List of tasks
     * @param ui        Ui provided
     * @param storage   Saved history
     * @return message that tells user action requested has been taken
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

}

