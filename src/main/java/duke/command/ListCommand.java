package duke.command;

import duke.logic.Storage;
import duke.logic.TaskList;
import duke.logic.Ui;

/**
 * Represents a command that displays a list of tasks.
 *
 * @author Peter
 */
public class ListCommand extends Command {
    /**
     * Displays a given list of tasks.
     *
     * @param taskList List of tasks that is to be displayed.
     * @param ui       UI responsible for displaying list of tasks.
     * @param storage  Storage ignored.
     * @return <code>true</code> upon successful execution.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String output = "YOUR TASKS:" + taskList;
        System.out.println(output);
        return output;
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }
}
