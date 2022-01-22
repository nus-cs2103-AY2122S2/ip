package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class MarkCommand extends Command {
    int index;

    public MarkCommand(int serialNumber) {
        this.index = serialNumber -1;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        taskList.get(this.index).done = true;
        storage.writeToFile(taskList);
        ui.outputMessage("Nice! I've marked this task as done: \n" +
                taskList.get(this.index));
    }
}
