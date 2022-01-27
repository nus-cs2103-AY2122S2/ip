package duke.command;

import duke.task.Task;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * The type List command.
 */
public class ListCommand extends Command {

    /**
     * Execute list all the task operation.
     *
     * @param taskList the task list to operate on
     * @param ui the ui to operate on
     * @param storage the storage to operate on
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskList.getTaskList().size() == 0) {
            ui.noTaskLeft();
        } else {
            for (int i = 0; i < taskList.getTaskList().size(); i++) {
                Task task = taskList.getTaskList().get(i);
                ui.listed(i + 1, task);
            }
        }
    }

    /**
     * The program is not yet exited.
     */
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null) {
            return obj instanceof ListCommand;
        } else {
            return false;
        }
    }
}
