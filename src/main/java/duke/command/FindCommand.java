package duke.command;

import duke.logic.Storage;
import duke.logic.TaskList;
import duke.logic.Ui;

/**
 * Represents a command that finds a task.
 *
 * @author Peter
 */
public class FindCommand extends Command {
    /**
     * Keyword that is to be used to filter a list of tasks.
     */
    private final String keyword;

    /**
     * Constructor for a find task command.
     *
     * @param keyword Keyword that is to be used to filter a list of tasks.
     */
    public FindCommand(String keyword) {
        super();
        this.keyword = keyword;
    }

    /**
     * Displays the filtered list of tasks that only contains the associated keyword.
     *
     * @param taskList List of tasks that is to be filtered.
     * @param ui       UI responsible for displaying filtered list of tasks.
     * @param storage  Storage ignored.
     * @return <code>true</code> upon successful execution.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList newTaskList = taskList.filter(this.keyword);
        String output = "MATCHING TASKS HERE:" + newTaskList;
        ui.showMessage("MATCHING TASKS HERE:" + newTaskList);
        return output;
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }
}
