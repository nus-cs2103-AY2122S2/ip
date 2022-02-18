package duke.command;

import duke.task.Task;
import duke.ui.Ui;
import duke.task.*;
import duke.storage.*;

import java.io.IOException;


public class AddCommand extends Command {
    private final Task Add;

    /**
     *
     * @param Add
     */
    public AddCommand(Task Add) {

        this.Add = Add;
    }

    /**
     *
     * @param taskList
     * @param ui
     * @param storage
     * @throws IOException
     */
    /** Adds a new command to the TaskList*/
    public String perform(TaskList taskList, Ui ui, Storage storage) throws IOException {
        taskList.addTask(this.Add);
        storage.update(taskList);
        return ui.printAddTask(this.Add, taskList);
    }
}