package duke.command;

import java.util.List;

import duke.Ui;
import duke.task.Task;

/**
 * Command invoked to exit the program.
 */
public class ByeCommand extends Command {

    /**
     * Execute method that should do nothing as the command exits.
     *
     * @param tasks Task list
     * @param ui UI object
     */
    @Override
    public void execute(List<Task> tasks, Ui ui) {
        //do nothing as it is a bye command
    }

    /**
     * Override method to ensure command exits the loop on the main method.
     *
     * @return Always true as command should exit
     */
    public boolean isExit() {
        return true;
    }
}
