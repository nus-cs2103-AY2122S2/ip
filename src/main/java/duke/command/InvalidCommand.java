package duke.command;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class InvalidCommand extends Command {

    public InvalidCommand(){}

    @Override
    public boolean exec(TaskList taskList, Ui ui, Storage storage) {
        ui.printInvalidCommand();
        return true;
    }
}
