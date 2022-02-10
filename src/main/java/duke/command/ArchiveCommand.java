package duke.command;

import duke.Storage;
import duke.TaskMaster;
import duke.Ui;
import duke.exception.DukeException;

/**
 * Deals with displaying the list of tasks for the user
 */
public class ArchiveCommand extends Command {

    /**
     * Constructor
     * @throws DukeException
     */
    public ArchiveCommand() throws DukeException {

    }

    @Override
    public String execute(TaskMaster taskMaster, Ui ui, Storage storage) {
        taskMaster.moveToArchives();
        storage.saveToFile(taskMaster.getCurrentTasks(), false);
        storage.saveToFile(taskMaster.getArchivedTasks(), true);
        return ui.notifyArchivedTasks();
    }
}
