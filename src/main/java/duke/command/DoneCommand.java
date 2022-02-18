package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class DoneCommand extends Command {
    private final int taskNum;

    /**
     *
     * @param taskNum
     */
    public DoneCommand(int taskNum) {

        this.taskNum = taskNum;
    }

    /**
     *
     * @param taskList
     * @param ui
     * @param storage
     * @throws IOException
     */
    /** Changes status of a  command to the done*/
    public String perform(TaskList taskList, Ui ui, Storage storage) throws IOException {
        assert this.taskNum > 0 : "duke.task.Task number must be greater than zero";
        taskList.markDone(this.taskNum - 1);
        storage.update(taskList);
        return ui.printMark(taskList.getTasks().get(this.taskNum - 1));
    }
}