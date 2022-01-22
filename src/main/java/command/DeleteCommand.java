package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class DeleteCommand extends Command {
    int index;

    public DeleteCommand(int serialNumber) {
        this.index = serialNumber -1;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        String taskDescription = taskList.get(this.index).description;
        taskList.delete(this.index);
        storage.writeToFile(taskList);
        ui.outputMessage("Noted. I've removed this task:\n" +
                taskDescription +
                "\nNow you have " + taskList.size() + " tasks in the list.");
    }
}
