package seedu.commands;

import seedu.duke.DukeException;
import seedu.storage.TaskList;
import seedu.task.Todo;

public class TodoCommand extends Command {

    private String description;

    @Override
    public void input(String input) throws DukeException {
        description = checkExist(input);
    }

    @Override
    public String execute(TaskList tasks) throws DukeException {
        Todo task = new Todo(description);
        tasks.add(task);
        return "New Todo: " + task.toString() + " added!";
    }
}
