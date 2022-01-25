package duke.command;

import java.util.List;
import duke.exception.DukeException;
import duke.task.Task;
import duke.UI;

public class ByeCommand extends Command{
    
    @Override
    public void execute(List<Task> tasks, UI ui) throws DukeException{
        //do nothing as it is a bye command
    }

    public boolean isExit(){
        return true;
    }
}
