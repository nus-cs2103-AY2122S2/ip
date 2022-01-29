package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

public abstract class Command {

    private boolean isBye;

    public Command() {
        isBye = false;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isExit(){
        return isBye;
    }

    public void setExit() {
        isBye = true;
    }

}
