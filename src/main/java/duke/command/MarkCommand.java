package duke.command;
import duke.DukeException;
import duke.task.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * MarkCommand represents the user's actions of marking/unmarking tasks.
 */
public class MarkCommand extends Command {

    // mark boolean is true indicates the mark command, false the unmark command
    private boolean mark;
    private String description;

    /**
     * Initializes the AddCommand with the CommandType Enum & a task description.
     * @param commandType User requested CommandType Enum 
     * @param description Task description 
     * @param mark Boolean that indicates if the task is to be marked/unmarked
     */
    public MarkCommand(CommandType commandType, String description, boolean mark) {
        super(commandType);
        this.mark = mark;
        this.description = description;
    }

    /**
     * The logic to execute the AddCommand
     * @param taskList The TaskList object containing existing tasks. 
     * @param ui The Ui object for interacting with the user. 
     * @param storage The Storage object for saving & loading tasks. 
     * @throws DukeException Error if the user missed a task index. 
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {

        // when the user input has the "mark" command & an integer 
        if (description.toLowerCase().matches(super.commandType.getRegex())) {
            try {
                int taskIndex = Integer.valueOf(description) - 1;
                
                // if user-specified task index is out of the list 
                if (taskIndex >= taskList.size() || taskIndex < 0) {
                    throw new DukeException("I'm sorry, you're referencing a task that does not exist!");
                }

                Task task = taskList.get(taskIndex);
                if (mark) {
                    task.markAsDone();
                    ui.showText("Okay, marking this task as done:");
                } else {
                    task.markAsUndone();
                    ui.showText("Okay, marking this task as not done yet:");
                }

                ui.showTask(task.toString());
                
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        } else {
            throw new DukeException("I'm sorry, you missed out the task index");
        }

        storage.updateFileContents(taskList);
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
