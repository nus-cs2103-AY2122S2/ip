package seedu.commands;

import seedu.storage.TaskList;

public class ByeCommand extends Command {

    @Override
    public void input(String input) {
        //not in use
    }

    @Override
    public String execute(TaskList tasks) {
        isExit = true;
        return "Good Bye!";
    }
}
