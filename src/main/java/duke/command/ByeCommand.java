package duke.command;

import java.util.List;
import duke.exception.DukeException;
import duke.task.Task;
import duke.Ui;

public class ByeCommand extends Command {
    
    @Override
    public void execute(List<Task> tasks, Ui ui) throws DukeException {
        //do nothing as it is a bye command
    }

    public boolean isExit() {
        return true;
    }
}
