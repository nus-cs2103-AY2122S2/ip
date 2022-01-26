package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;
import duke.managers.Ui;
import duke.managers.Storage;

/**
 * Represents a generic store command recognized by the parser.
 * Upon execution, StoreCommand objects will attempt to store a task into
 * the task list.
 * StoreCommand object stores the tokens which represents the input of the user.
 */
public abstract class StoreCommand extends Command {
    protected String[] tokens;
    public StoreCommand(String[] tokens) {
        this.tokens = tokens;
    }

    /**
     * Executes the StoreCommand object, at the end of execution all
     * StoreCommand saves the task list to file.
     *
     * @param taskList a container of existing tasks in the program.
     * @param io a manager that deals with interactions with the user.
     * @param storage a manager that deals with storing and loading of files, used to save
     *                changes to the task list to file.
     */
    @Override
    public void execute(TaskList taskList, Ui io, Storage storage) throws DukeException {
        storage.save(taskList);
    }
}
