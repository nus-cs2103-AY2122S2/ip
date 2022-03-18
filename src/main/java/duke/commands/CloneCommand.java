package duke.commands;

import duke.admin.Storage;
import duke.admin.TaskList;
import duke.admin.Ui;
import duke.exceptions.DukeException;
import duke.tasks.Task;

/**
 * CloneCommand clones the indexed task specified in the command.
 */
public class CloneCommand extends Command {
    private String description;

    /**
     * Constructor for CloneCommand that takes in a description.
     * @param description of clone command.
     */
    public CloneCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        int index = Integer.parseInt(description) - 1;

        if (index >= tasks.getNumberOfTasks() || index < 0) {
            throw new DukeException(DukeException.INVALID_FORMAT);
        }

        Task task = tasks.clone(index);
        storage.updateAfterClone(index);

        return Ui.showClonedMessage(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
