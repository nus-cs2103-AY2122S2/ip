package pac.command;

import pac.storage.Storage;
import pac.task.TaskList;
import pac.ui.Ui;

import java.io.IOException;

/**
 *  Executes the find command for tasks
 *  returns the ui message for Pac response
 */
public class FindCommand extends Command{
    private final String keyword;

    /**
     * Find Command constructor takes in a String
     * @param keyword
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     *
     * @param tasks
     * @param ui
     * @param storage
     * @return
     * @throws IOException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        TaskList matchingTasks = tasks.find(keyword);
        return ui.showFind(matchingTasks);
    }
}
