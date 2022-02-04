package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * This MarkCommand class will mark a task as done
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";
    private int taskIndex;

    /**
     * Constructor for MarkCommand with a given task number to be marked as done in the list.
     *
     * @param taskIndex 0-based index task number to be marked.
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes command by marking task in TaskList.
     * @param taskList  List of tasks
     * @param ui        Ui provided
     * @param storage   Saved history
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String output = taskList.markTask(taskIndex);
        Storage.saveToFile(taskList.getCurrentList());
        return output;
    }
}
