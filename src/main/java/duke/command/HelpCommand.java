package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * This HelpCommand class will show a list of commands that the user can type in
 * and the format in which they should provide an input
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";
    public static final String COMMAND_EXAMPLE = "help me!";

    public HelpCommand() {
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
        return ui.showHelpMessage();
    }
}
