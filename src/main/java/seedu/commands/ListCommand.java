package seedu.commands;

import seedu.storage.TaskList;

public class ListCommand extends Command {

    @Override
    public void input(String input) {
        // not in use
    }

    @Override
    public String execute(TaskList tasks) {
        return tasks.toString();
    }
}
