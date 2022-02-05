package duke.command;
import duke.task.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.gui.Ui;

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
    public String execute(TaskList taskList, Ui ui, Storage storage) {

        if (taskList.isEmpty()) {
            ui.addText("Your list is empty");
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                ui.listTask(i, task);
            }
        }
        return ui.generateDukeResponse();
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
