package duke.command;
import duke.DukeException;
import duke.task.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class MarkCommand extends Command {

    // mark boolean is true indicates the mark command, false the unmark command
    private boolean mark;
    private String description;

    public MarkCommand(CommandType commandType, String description, boolean mark) {
        super(commandType);
        this.mark = mark;
        this.description = description;
    }

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

    @Override
    public boolean isActive() {
        return super.active;
    }
    
}
