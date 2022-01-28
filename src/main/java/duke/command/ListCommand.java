package duke.command;
import duke.task.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {

    public ListCommand(CommandType commandType) {
        super(commandType);
    }

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

    @Override
    public boolean isActive() {
        return super.active;
    }
    
}
