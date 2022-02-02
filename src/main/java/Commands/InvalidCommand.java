package Commands;

import Tasks.TaskList;
import util.Storage;
import util.Ui;

public class InvalidCommand extends DukeCommand {

    public InvalidCommand(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return this.commandBody;
    }
}
