package bobby.command;

import bobby.Storage;
import bobby.Ui;
import bobby.task.TaskList;

/**
 * Represents a 'bye' command.
 */
public class ByeCommand extends Command {
    /**
     * Creates a ByeCommand Object
     */
    public ByeCommand(){
    }

    /**
     * {@inheritDoc}
     *
     * @param tasks   TaskList object containing a list of Tasks.
     * @param ui      Ui object to allow for Bobby to print messages.
     * @param storage Storage object that handles the reading/writing of TaskList into a specified file.
     * @return Bobby's reply to the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";
        storage.saveTasks(tasks.getTaskList());
        return ui.goodbyeMessage();
    }

    /**
     * Overrides the default equals() method. Compares if 2 objects are of the same Command type.
     *
     * @param obj The other Command object to compare with.
     * @return True if both are ByeCommand objects. False otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof ByeCommand;
    }
}
