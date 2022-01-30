package duke.command;
import duke.task.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * ListCommand represents the user's actions of listing all existing tasks.
 */
public class ListCommand extends Command {

    /**
     * Initializes the ListCommand with the CommandType Enum.
     * @param commandType
     */
    public ListCommand(CommandType commandType) {
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
        if (taskList.isEmpty()) {
            ui.showText("Your list is empty");
        }
        else {
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                ui.showText((i+1) + ". " + task.toString());
            }
        }
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
