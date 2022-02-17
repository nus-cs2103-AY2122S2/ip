package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ExitCommand extends Command {
    /**
     *
     * @param taskList
     * @param ui
     * @param storage
     */
    /** Changes status of a  command to the exit*/
    public String perform(TaskList taskList, Ui ui, Storage storage) {

        super.Exit();
        return "Bye. See you again";

    }
}