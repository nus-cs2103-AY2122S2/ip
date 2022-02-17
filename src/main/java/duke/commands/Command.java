package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.DukeUnsupportedOperationException;
import duke.tasks.TaskList;

public class Command {

    static TaskList taskList;

    public static void defineTaskList(TaskList taskList) {
        Command.taskList = taskList;
    }

    public String execute() throws DukeException{
        throw new DukeUnsupportedOperationException("This method is to be implemented by child classes");
    }
}
