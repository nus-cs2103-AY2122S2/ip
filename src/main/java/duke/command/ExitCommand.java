package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Responsible for the functionality needed when exiting the chat bot.
 */
public class ExitCommand extends Command{

    /** Constructor to create ExitCommand. */
    public ExitCommand() {
        super(CommandType.EXIT);
    }

    /**
     * Outputs the exit message via the user interface.
     *
     * @param taskList list of tasks.
     * @param ui user interface of the chat bot.
     * @param storage storage used by chat bot.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
            ui.showExit();
    }
}
