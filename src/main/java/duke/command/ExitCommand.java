package duke.command;
import duke.task.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ExitCommand extends Command {

    public ExitCommand(CommandType commandType) {
        super(commandType);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showGoodbye();
        super.active = false;
    }

    @Override
    public boolean isActive() {
        return super.active;
    }
    
}
