package duke.command;
import duke.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

public class AddCommand extends Command {

    private String description;

    public AddCommand(CommandType commandType, String description) {
        super(commandType);
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {

        // check if the user's input is just whitespaces
        if (description.trim().length() == 0) {
            throw new DukeException("I'm sorry, your todo command is missing a task description. Please try again."); 
        }

        Task task;

        switch (super.commandType) {
        case TODO:
            task = new Todo(description, false); 
            taskList.add(task);
            ui.showText("Noted. I've added this task: ");
            ui.showTask(task.toString());
            break;

        case EVENT: 
            // Fallthrough 
        case DEADLINE:
            String[] taskDescriptionStrings = description.split(super.commandType.getRegex());
            String taskDescriptionText = taskDescriptionStrings[0].strip();
            String taskDescriptionTime = taskDescriptionStrings[1].strip();
    
            // check if the user's input is just whitespace 
            if (taskDescriptionText.strip().length() == 0) {
                throw new DukeException("I'm sorry, your " + commandType + " command is missing a task description. Please try again.");
            } else if (taskDescriptionTime.strip().length() == 0) {
                throw new DukeException("I'm sorry, your " + commandType + " command is missing a time. Please try again.");
            }
    
            try {
                if (commandType.equals(CommandType.EVENT)) {
                    task = new Event(taskDescriptionText, taskDescriptionTime, false);
                } else {
                    task = new Deadline(taskDescriptionText, taskDescriptionTime, false);
                }
                
                taskList.add(task);
                ui.showText("Noted. I've added this task: ");
                ui.showText(task.toString());
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
            break;
        }

        storage.updateFileContents(taskList);
    }

    @Override
    public boolean isActive() {
        return super.active;
    }
    
}
