package duke.commands;

import duke.Storage;
import duke.TextUi;
import duke.exceptions.UndoException;
import duke.tasks.TaskList;

/**
 * Represents a command that allows users to exit the duke chat bot.
 */
public class Bye extends Command {
    /**
     * Returns a string that indicates the exiting of the Duke program.
     * The JavaFX program will be terminated upon receiving this "bye" message.
     * @param taskList A taskList containing all existing tasks in Duke.
     * @param ui A UI object that is used to print the System's response.
     * @param storage A storage object that is able to read and write to storage file.
     * @return An exit message to signal the termination of the program.
     */
    @Override
    public String execute(TaskList taskList, TextUi ui, Storage storage) {
        return "bye";
    }

    /**
     * Returns an UndoException since a bye command cannot be undone.
     * @param taskList A taskList containing all existing tasks in Duke.
     * @throws UndoException since the program is unable to undo a list command.
     **/
    @Override
    public String undo(TaskList taskList) throws UndoException {
        throw new UndoException("NOTHING");
    }
}
