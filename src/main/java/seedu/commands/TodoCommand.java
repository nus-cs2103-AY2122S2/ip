package seedu.commands;

import seedu.duke.DukeException;
import seedu.storage.TaskList;
import seedu.task.Todo;

/**
 * The Todo Command
 */
public class TodoCommand extends Command {

    private String description;

    @Override
    public void validate(String input) throws DukeException {
        description = checkExist(input);
    }

    /**
     * Creates and adds a new task to the list
     *
     * @param tasks The task list in question
     * @return A string showing the task was actually added
     */
    @Override
    public String execute(TaskList tasks) {
        Todo task = new Todo(description);
        tasks.add(task);
        return "New Todo: " + task.toString() + " added!";
    }
}
