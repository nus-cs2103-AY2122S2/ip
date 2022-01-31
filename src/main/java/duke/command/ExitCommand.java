package duke.command;
import duke.task.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * ExitCommand represents the user's actions of exiting the Duke program.
 */
public class ExitCommand extends Command {

    /**
     * Initializes the AddCommand with the CommandType Enum.
     * @param commandType
     */
    public ExitCommand(CommandType commandType) {
        super(commandType);
    }

    /**
     * The logic to execute the AddCommand
     * @param taskList The TaskList object containing existing tasks.
     * @param ui The Ui object for interacting with the user.
     * @param storage The Storage object for saving & loading tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showGoodbye();
        super.active = false;
    }

    /**
     * A getter method to indicate if the chat session with Duke is active.
     * @return boolean indicating if the chat session is active or not.
     */
    @Override
    public boolean isActive() {
        return super.active;
    }

}
