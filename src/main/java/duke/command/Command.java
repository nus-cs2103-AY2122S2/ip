package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.utility.UI;
import duke.utility.Storage;

/**
 * convert user input into duke.command
 */
public abstract class Command {

    String command;

    Command(String command){
        this.command = command;
    }

    public abstract String execute(TaskList tasks, UI ui, Storage storage) throws DukeException;

    public abstract boolean isExit();

}
