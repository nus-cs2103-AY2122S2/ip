package duke.command;

import duke.task.TaskList;

public class UnrecognizedCommand extends Command {

    /**
     * Constructs a {@code UnrecognizedCommand} object.
     */
    public UnrecognizedCommand() {}

    /**
     * Pushes a message to the UI to indicate that the command is not recognized.
     * @param tasks current list of tasks
     */
    @Override
    public String execute(TaskList tasks) {
        return "Sorry, but I don't know what that means :(";
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
