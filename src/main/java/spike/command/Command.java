package spike.command;

import spike.task.Task;
import spike.task.TaskList;

/**
 * Specifies behaviours of various types of commands.
 */
public class Command {
    // TODO one execute function, then use polymorphism, allow different command to execute themselves


    public String execute(TaskList task) {
        return "";
    }


}
