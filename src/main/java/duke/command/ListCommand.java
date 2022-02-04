package duke.command;

import duke.Storage;
import duke.TaskMaster;
import duke.Ui;
import duke.exception.DukeException;

/**
 * Deals with displaying the list of tasks for the user
 */
public class ListCommand extends Command {

    /**
     * Constructor
     * @throws DukeException
     */
    public ListCommand() throws DukeException {

    }

    /**
     * Prints out all the tasks in the list for the user to see.
     * @param tasks holds all the tasks that the user has recorded down.
     * @param ui used to notify the user of task completion.
     * @param storage saves the tasks to file if there were any edits to it.
     */
    @Override
    public String execute(TaskMaster tasks, Ui ui, Storage storage) {
        return ui.printTasks(tasks.getTasks());
    }
}
