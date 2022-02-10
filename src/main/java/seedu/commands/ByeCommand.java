package seedu.commands;

import seedu.storage.TaskList;

/**
 * The Bye Command
 */
public class ByeCommand extends Command {

    @Override
    public void input(String input) {
        //not in use
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
        isExit = true;
        return "Good Bye!";
    }
}
