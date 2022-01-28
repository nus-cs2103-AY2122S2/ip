package duke.command;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class InvalidCommand extends Command {
    String errorMessage;

    public InvalidCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public boolean exec(TaskList taskList, Ui ui, Storage storage) {
        ui.print(errorMessage);
        return true;
    }
}
