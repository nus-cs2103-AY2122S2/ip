package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public abstract class Command {
    private boolean exit = false;

    /**
     *
     * @param taskList
     * @param ui
     * @param storage
     * @throws IOException
     */
    public abstract String perform(TaskList taskList, Ui ui, Storage storage) throws IOException;


    public void Exit() {

        this.exit = !this.exit;
    }


    public boolean isExit() {

        return this.exit;
    }
}