package duke.command;
import duke.DukeException;
import duke.task.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {

    private String description;

    public DeleteCommand(CommandType commandType, String description) {
        super(commandType);
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            // check if the user input an int 
            if (!description.trim().matches("\\d+")) {
                throw new DukeException("I'm sorry, you missed out the task index");
            }
            
            int taskIndex = Integer.valueOf(description.trim()) - 1;

            // if user-specified task index is out of the list 
            if (taskIndex >= taskList.size()) {
                throw new DukeException("I'm sorry, you're referencing a task that does not exist!");
            }

            Task task = taskList.remove(taskIndex);
            ui.showText("Okay, I've deleted this task");
            ui.showTask(task.toString());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }

        storage.updateFileContents(taskList);
    }

    @Override
    public boolean isActive() {
        return super.active;
    }
    
}
