package duke.commands;

import duke.exception.DukeException;

public abstract class Command<T> {
    private void execute() throws DukeException, DukeException {}
    public boolean isExit(){
        return true;
    }
}
