package duke.commands;

import java.util.ArrayList;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Deals with handling command that find tasks.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructor for FindCommand.
     *
     * @param keyword keyword of the task user want to find.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Adds task into task list and print out message to inform user.
     *
     * @param tasks List of the tasks.
     * @param ui UI that deals with interactions with the user.
     * @param storage storage handles the saving and writing to file.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> searchResultList = tasks.findTask(keyword);
        ui.displaySearchResult(searchResultList);
    }

    /**
     * Checks if the user is exiting the program.
     *
     * @return false that user not exiting.
     */
    public boolean isExit() {
        return false;
    }
}
