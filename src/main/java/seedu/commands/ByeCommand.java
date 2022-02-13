package seedu.commands;

import seedu.storage.TaskList;

/**
 * The Bye Command
 */
public class ByeCommand extends Command {

    private boolean state;

    @Override
    public void input(String input) {
        // input is not in use in this scenario
        state = true;
    }

    /**
     * Sets the boolean flag to tell the program to exit
     *
     * @param tasks The task list in question
     * @return A string to be printed in the console
     */
    @Override
    public String execute(TaskList tasks) {
        // Task list is not in use in this scenario
        isExit = state;
        return "Good Bye!";
    }
}
