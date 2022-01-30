package duke.command;

import duke.ui.Storage;
import duke.ui.TaskList;
import duke.ui.Ui;

/**
 * An extension from the Command class.
 * Deals with the finding of a matching task
 * from a given taskList.
 * String variable match is specific to this class.
 */
public class FindCommand extends Command {

    private final String match;

    /**
     * Constructs a new FindCommand Class with
     * variable, match.
     * @param match matching keyword
     */
    public FindCommand(String match) {
        this.match = match;
    }

    /**
     * Returns a string indicating the result of finding a matching task
     * from the taskList.
     * Executes a method from taskList to find a matching
     * task with the word from the task.
     * @param taskList a list of the current tasks
     * @param ui user interface
     * @param storage file storage
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String statement = "Here are the matching tasks in your list:\n";
        return statement + taskList.findMatching(match);
    }

    /**
     * Returns false for non-Exit commands.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
