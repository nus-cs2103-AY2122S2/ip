package duke.commands;

import duke.tasks.TaskList;
/**
 * Represents a <code>Byecommand</code> which is called when the program exits.
 */
public class ByeCommand implements Command {

    @Override
    public String execute(TaskList tasks) {
        return "";
    }

}
