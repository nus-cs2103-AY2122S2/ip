package seedu.commands;

import seedu.duke.DukeException;
import seedu.storage.TaskList;
import seedu.task.Todo;

/**
 * The Todo Command
 */
public class TodoCommand extends Command {

    private String description;

    /**
     * Checks whether task exists
     *
     * @param input The task itself
     * @throws DukeException There is no task
     */
    @Override
    public void validate(String input) throws DukeException {
        description = checkExist(input);
    }

    /**
     * Adds todo task into list
     *
     * @param tasks The task list
     * @return The newly created todo task
     */
    @Override
    public String execute(TaskList tasks) {
        Todo task = new Todo(description);
        tasks.add(task);
        return super.show("New Todo:", task);
    }
}
