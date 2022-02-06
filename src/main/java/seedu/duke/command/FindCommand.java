package seedu.duke.command;

import seedu.duke.chatbot.Storage;
import seedu.duke.chatbot.Ui;
import seedu.duke.exceptions.DukeException;
import seedu.duke.task.TaskList;

/**
 * Created when user inputs a command to search for a {@link seedu.duke.task.Task} in {@link TaskList}.
 */
public class FindCommand extends Command {
    private final String searchTerm;

    /**
     * Creates a FindCommand object.
     * @param searchTerm which is the string that the {@link seedu.duke.task.Task} needs to contain.
     */
    public FindCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    /**
     * {inheritDoc}.
     */
    @Override
    public TaskList execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        TaskList newTaskList = taskList.find(this.searchTerm);
        return taskList; //return the old tasklist because the new one is a filtered version
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public String getResponseAfterCommand(TaskList taskList) {
        return Ui.showCompletedSearch(taskList);
    }

    /**
     * {inheritDoc}.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
