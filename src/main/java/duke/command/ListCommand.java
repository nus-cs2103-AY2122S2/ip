package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {
    /**
     *
     * @param taskList
     * @param ui
     * @param storage
     */
    /** Lists out all the commands*/
    public String perform(TaskList taskList, Ui ui, Storage storage) {

       return ui.printTasks(taskList);
    }
}