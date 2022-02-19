package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * This ByeCommand class will print the exit message when executed.
 */
public class ByeCommand extends Command {

    public static final String COMMAND_WORD = "bye";
    public static final String COMMAND_EXAMPLE = "bye dave!";

    public ByeCommand() {
        super(COMMAND_WORD, COMMAND_EXAMPLE);
    }

    /**
     * Executes command by printing exit message.
     *
     * @param taskList List of tasks.
     * @param ui       Ui provided.
     * @param storage  Saved history.
     * @return message to tell user that chat-bot program has been terminated.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        return ui.showExitMessage();
    }
}
