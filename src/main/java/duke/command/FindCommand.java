package duke.command;

import duke.Storage;
import duke.TaskMaster;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * Responsible for finding tasks that are relevant to the user's search keyword
 */
public class FindCommand extends Command{

    private String keyword;

    /**
     * FindCommand constructor.
     * @param keyword String that is to be matched on.
     * @throws DukeException
     */
    public FindCommand(String keyword) throws DukeException{
        this.keyword = keyword;
    }

    /**
     * Calls TaskMaster method to search for the given keyword and passes the list to storage to print.
     * @param tasks   holds all the tasks that the user has recorded down and searches for relevant tasks.
     * @param ui      used to notify the user of tasks found.
     * @param storage saves the tasks to file if there were any edits to it.
     */
    @Override
    public void execute(TaskMaster tasks, Ui ui, Storage storage) {
        ui.printTasks(tasks.findTasks(this.keyword));
    }

}
