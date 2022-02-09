package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * This UnmarkCommand class will mark a task as undone
 */
public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";
    public static final String COMMAND_EXAMPLE = "unmark 1";
    private int taskIndex;

    /**
     * Constructor for MarkCommand with a given task number to be marked as done in the list.
     *
     * @param taskIndex 0-based index task number to be marked.
     */
    public UnmarkCommand(int taskIndex) {
        super(COMMAND_WORD, COMMAND_EXAMPLE);
        this.taskIndex = taskIndex;
    }

    /**
     * Executes command by unmarking task in TaskList.
     *
     * @param taskList List of tasks
     * @param ui       Ui provided
     * @param storage  Saved history
     * @return message to tell user that task has been unmarked
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String output = taskList.unmarkTask(taskIndex);
        Storage.saveToFile(taskList.getCurrentList());
        return output;
    }
}
