package seedu.commands;

import seedu.duke.DukeException;
import seedu.duke.Ui;
import seedu.storage.Storage;
import seedu.storage.TaskList;
import seedu.task.Todo;

public class TodoCommand extends Command {

    private String description;

    @Override
    public void input(String input) throws DukeException {
        description = checkExist(input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Todo task = new Todo(description);
        tasks.add(task);
        ui.printDone(task, " added!");
    }
}
