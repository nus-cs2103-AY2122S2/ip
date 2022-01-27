package command;

import exception.DukeException;
import task.TaskList;
import utility.Input;
import utility.Storage;

public abstract class Command {

    String command;

    Command(String command){
        this.command = command;
    }

    public abstract void execute(TaskList tasks, Input input, Storage storage) throws DukeException;

    public abstract boolean isExit();



}
