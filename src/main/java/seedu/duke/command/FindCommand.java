package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.exceptions.DukeException;
import seedu.duke.task.TaskList;

public class FindCommand extends Command {
    private final String searchTerm;

    public FindCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    @Override
    public TaskList execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        TaskList newTaskList = taskList.find(this.searchTerm);
        ui.showCompletedSearch(newTaskList);
        return taskList; //return the old tasklist because the new one is a filtered version
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
