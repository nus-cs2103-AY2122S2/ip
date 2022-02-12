package duke.command;

import duke.task.TaskList;

/**
 * A Command indicating the command is not recognized.
 */
public class UnrecognizedCommand extends Command {

    /**
     * Constructs a {@code UnrecognizedCommand} object.
     */
    public UnrecognizedCommand() {}

    /**
     * Returns a message indicating that the command is not recognized.
     * @param tasks current list of tasks
     */
    @Override
    public String execute(TaskList tasks) {
        return "Sorry, but I don't know what that means :(";
    }

    /**
     * Indicates that the program should not be exited.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
