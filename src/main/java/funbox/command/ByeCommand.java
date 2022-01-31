package funbox.command;

import funbox.util.Ui;
import funbox.util.TaskList;
import funbox.util.Storage;

/**
 * Deal with handling command that user exit.
 */
public class ByeCommand extends Command {
    /**
     * Constructor for ByeCommand Class.
     */
    public ByeCommand() {
        super(true);
    }

    /**
     * Execute the command and returns a goodbye message to users.
     *
     * @param taskList List of tasks.
     * @param ui Interface which interact with users.
     * @param storage Stores user tasks locally.
     * @return Returns a string to be displayed to users.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.printBye();
    }
}
