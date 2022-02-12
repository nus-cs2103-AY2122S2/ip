package duke.command;

import duke.task.TaskList;

/**
 * A Command that signals to exit the program when executed.
 */
public class ByeCommand extends Command {

    /**
     * Constructs a {@code ByeCommand} object.
     */
    public ByeCommand() {}

    /**
     * Pushes a goodbye message to the UI.
     * @param tasks current list of tasks
     */
    @Override
    public String execute(TaskList tasks) {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Indicates that the program should be exited.
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }

}
