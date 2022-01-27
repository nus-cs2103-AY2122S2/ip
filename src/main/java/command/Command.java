package command;

import exception.DukeException;
import task.TaskList;
import utility.Input;

public abstract class Command {

    String command;

    Command(String command){
        this.command = command;
    }

    public abstract void execute(TaskList tasks, Input input) throws DukeException;

    public abstract boolean isExit();



}
