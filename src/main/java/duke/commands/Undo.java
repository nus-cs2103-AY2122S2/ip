package duke.commands;

import duke.Storage;
import duke.TextUi;
import duke.exceptions.DukeException;
import duke.exceptions.UndoException;
import duke.tasks.TaskList;

/**
 * Represents a command that allows the user to undo the most recent command.
 * Commands that can be undone: AddDeadline, AddEvent, AddTodo, Delete, Mark, Unmark.
 */
public class Undo extends Command {
    private final Command previousCommand;

    /**
     * Instantiates an undo command.
     * @param previousCommand The command that the system needs to undo.
     */
    public Undo(Command previousCommand) {
        this.previousCommand = previousCommand;
    }

    /**
     * Returns a success message after the most recent command has been undone.
     * Throws an error if there are no previous commands left to undo.
     * @param taskList A taskList containing all existing tasks in Duke.
     * @param ui A UI object that is used to print the System's response.
     * @param storage A storage object that is able to read and write to storage file.
     * @return A success message after a particular task has successfully been undone.
     */
    @Override
    public String execute(TaskList taskList, TextUi ui, Storage storage) throws DukeException {
        if (previousCommand != null) {
            return previousCommand.undo(taskList);
        }
        throw new UndoException("EMPTY_PREVIOUS");
    }
}

