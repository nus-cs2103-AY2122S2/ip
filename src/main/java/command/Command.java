package command;

import exception.DukeException;
import task.TaskList;
import utility.UI;
import utility.Storage;

public abstract class Command {

    String command;

    Command(String command){
        this.command = command;
    }

    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws DukeException;

    public abstract boolean isExit();



}
