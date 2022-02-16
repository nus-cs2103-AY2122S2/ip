package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates a command that finds a task containing a given string.
 */
public class FindCommand extends Command {

    private final String searchString;

    /**
     * Initialises a FindCommand instance given a string to search for.
     *
     * @param searchString the string to search for in the task list.
     */
    public FindCommand(String searchString) {
        this.searchString = searchString;
    }

    /**
     * Finds tasks containing this FindCommand's searchString
     * displays the tasks found.
     *
     * @param taskList the task list to search through.
     * @param ui the user interface of Duke.
     * @param storage the storage of Duke.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList findResults = new TaskList();
        for (int i = 1; i < taskList.getLength() + 1; i++) {
            Task currTask = taskList.getTask(i);
            assert currTask != null : "task retrieved from taskList cannot be null";

            if (currTask.getDescription().contains(this.searchString)) {
                findResults.addTask(currTask);
            }
        }
        return ui.showFindResult(findResults);
    }

    /**
     * Checks whether the Duke application should exit after this command.
     *
     * @return true iff this Command is a ByeCommand instance.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
