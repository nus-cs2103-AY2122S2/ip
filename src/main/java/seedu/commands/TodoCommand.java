package seedu.commands;

import seedu.duke.DukeException;
import seedu.storage.TaskList;
import seedu.task.Todo;

public class TodoCommand extends Command {

    private String description;

    @Override
    public void validate(String input) throws DukeException {
        description = checkExist(input);
    }

    @Override
    public String execute(TaskList tasks) {
        Todo task = new Todo(description);
        tasks.add(task);
        return super.print("New Todo:", task);
    }
}
