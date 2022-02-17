package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {
    private final int taskNum;

    /**
     *
     * @param taskNum
     */
    public DeleteCommand(int taskNum) {

        this.taskNum = taskNum;
    }

    /**
     *
     * @param taskList
     * @param ui
     * @param storage
     * @throws IOException
     */
    public String perform(TaskList taskList, Ui ui, Storage storage) throws IOException {
        assert this.taskNum > 0 : "duke.task.Task number must be greater than zero";
        Task toDelete = taskList.getTasks().get(this.taskNum - 1);
        taskList.deleteTask(this.taskNum - 1);
        storage.update(taskList);
        return ui.printDelete(toDelete, taskList);
    }
}