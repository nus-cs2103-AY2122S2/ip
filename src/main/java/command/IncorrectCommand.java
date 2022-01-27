package command;

import exception.DukeException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class IncorrectCommand extends Command {
    protected String errorMessage = "";
    public IncorrectCommand(){

    }

    public IncorrectCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.errorMessage.equals(""))
            throw new DukeException(ui.showIncorrectMessage());
        else
            throw new DukeException(this.errorMessage);
    }

}
