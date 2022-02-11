package duke.command;

import duke.logic.Storage;
import duke.logic.TaskList;
import duke.logic.TaskStack;
import duke.logic.Ui;

/**
 * Represents a command that finds a task.
 *
 * @author Peter
 */
public class FindCommand extends Command {
    /**
     * Keywords that are to be used to filter a list of tasks.
     */
    private final String[] keywords;

    /**
     * Constructor for a find task command.
     *
     * @param keywords Keywords that are to be used to filter a list of tasks.
     */
    public FindCommand(String[] keywords) {
        super();
        this.keywords = keywords;
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
    public String execute(TaskList taskList, Ui ui, Storage storage, TaskStack taskStack) {
        TaskList newTaskList = taskList.filter(this.keywords);
        String output = "MATCHING TASKS HERE:" + newTaskList;
        ui.showMessage(output);
        return output;
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }
}
