package bobby.command;

import bobby.Storage;
import bobby.Ui;
import bobby.task.TaskList;

/**
 * Represents a 'list' command
 */
public class ListCommand extends Command {

    /**
     * Creates a ListCommand object.
     */
    public ListCommand() {
    }

    /**
     * Carries out the respective command's actions.
     *
     * @param tasks TaskList object containing a list of Tasks.
     * @param ui Ui object to allow for Bobby to print messages.
     * @param storage Storage object that handles the reading/writing of TaskList into a specified file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printLongLine();
        tasks.sortTaskList();
        ui.printTaskList(tasks);
    }

    /**
     * Overrides the default equals() method. Compares if 2 objects are of the same Command type.
     *
     * @param obj The other Command object to compare with.
     * @return True if both are InvalidCommand objects. False otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof ListCommand;
    }
}
