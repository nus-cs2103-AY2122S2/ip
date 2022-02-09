package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command that when executed shows the newly created alias of command.
 */
public class AliasCommand extends Command {
    private final String from;
    private final String to;

    /**
     * Class constructor.
     *
     * @param from the new alias created.
     * @param to the actual command the new alias is referring to.
     */
    public AliasCommand(String from, String to) {
        this.from = from;
        this.to = to;
    }

    /**
     * Shows user the result of aliasing a command.
     *
     * @param ui user interface of the application.
     * @param taskList task list of the application.
     * @param storage disk storage of the application.
     */
    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        assert ui != null;
        ui.showMessage("Aliases created: " + from + " now refers to " + to);
    }
}
