package duke.command;

import duke.common.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Class representing the functionality of Task
 */
public abstract class Command {

    /**
     * Keeps track of the Type of command.
     */
    private CommandType commandType;

    /**
     * Constructor to create Command object
     */
    public Command(CommandType commandType) {
        this.commandType = commandType;
    }

    /**
     * Abstract method to implement the functionality of the command
     * of the command of the child classes.
     *
     * @param taskList list of tasks.
     * @param ui user interface of the chat bot.
     * @param storage storage used by chat bot.
     * @throws DukeException if there is an error when executing.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Outputs whether the command is an Exit Command.
     *
     * @return True if command is Exit, false otherwise.
     */
    public Boolean isExit() {
        return commandType.equals(CommandType.EXIT);
    }

}
