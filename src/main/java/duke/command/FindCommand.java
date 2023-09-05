package duke.command;

import duke.main.Storage;
import duke.task.Task;
import duke.main.Ui;
import duke.task.TaskList;
import java.util.ArrayList;

/**
 * Represents find command
 */
public class FindCommand extends Command{
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds matching tasks with specified keyword
     *
     * @param tasks contains list of tasks
     * @param ui interact with user
     * @param storage save tasks to file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = tasks.findTasks(keyword);
        ui.showMatchingTasks(matchingTasks);
    }
}
