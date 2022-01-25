package duke.command;

import java.util.List;
import duke.exception.DukeException;
import duke.task.Task;
import duke.UI;

public abstract class Command {
    public abstract void execute(List<Task> tasks, UI ui) throws DukeException;
    public boolean isExit(){
        return false;
    }
}
