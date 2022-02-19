package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * This ListCommand class will show a list of added tasks when executed.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String COMMAND_EXAMPLE = "list tasks";

    public ListCommand() {
        super(COMMAND_WORD, COMMAND_EXAMPLE);
    }

    /**
     * Executes command by adding task into Duke.Duke.util.TaskList.
     *
     * @param taskList List of tasks
     * @param ui       Ui provided
     * @param storage  Saved history
     * @return message to tell user of the outstanding tasks in the list
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String output = taskList.printTaskList();
        return output;
    }
}
