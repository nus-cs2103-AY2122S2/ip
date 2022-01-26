package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index, String[] commandArray) {
        super(commandArray);
        this.index = index;
    }

    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage) {
        taskList.deleteTask(index);
        storage.save(taskList);
    }

    @Override
    public boolean equals(Object command) {
        if (this.index == ((DeleteCommand) command).index) {
            return true;
        } else {
            return false;
        }
    }
}
