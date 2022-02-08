package duke.commands;

import duke.Storage;
import duke.TextUi;
import duke.exceptions.DukeException;
import duke.exceptions.UndoException;
import tasks.TaskList;

/**
 * Represents a command that allows the user to undo the most recent command
 */
public class Undo extends Command {
    private final Command previousCommand;

    /**
     * Instantiates an undo command
     * @param previousCommand stored previous command that we need to undo
     */
    public Undo(Command previousCommand) {
        this.previousCommand = previousCommand;
    }
    /**
     * Method that executes a command to undo the most recent command the user has keyed in
     * @param taskList a taskList containing all existing tasks
     * @param ui a ui object
     * @param storage a storage object that is able to read and write to storage file
     * @return message after a task has been marked
     */
    @Override
    public String execute(TaskList taskList, TextUi ui, Storage storage) throws DukeException {
        if (previousCommand != null) {
            return previousCommand.undo(taskList);
        }
        throw new UndoException("EMPTY_PREVIOUS");
    }
}

