package duke.commands;

import duke.Duke;
import duke.data.TaskList;
import duke.storage.Storage;

/**
 * Represents an executable command.
 */
public abstract class Command {
    protected TaskList taskList;
    protected Duke duke;
    protected Storage storage;

    /**
     * Executes the command and returns the result.
     *
     * @return result of the execution.
     * @throws Exception if there is an error executing the command.
     */
    public abstract String execute() throws Exception;

    /**
     * Supplies the data the command will operate on.
     *
     * @param taskList task list.
     * @param duke the duke object.
     * @param storage the storage object.
     */
    public void setData(TaskList taskList, Duke duke, Storage storage) {
        this.taskList = taskList;
        this.duke = duke;
        this.storage = storage;
    }
}
