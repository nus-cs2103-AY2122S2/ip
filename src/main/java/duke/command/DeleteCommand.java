package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * This DeleteCommand class will delete a task from the list of tasks.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    private final int taskIndex;

    /**
     * Constructor for DeleteCommand which provides an index to delete.
     *
     * @param taskIndex 0-based index.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }


    /**
     * Executes command by printing exit message.
     * @param taskList List of tasks.
     * @param ui       Ui provided.
     * @param storage  Saved history.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String output = taskList.delete(this.taskIndex);
        Storage.saveToFile(taskList.getCurrentList());
        return output;
    }
}
