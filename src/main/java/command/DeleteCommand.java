package command;

import exception.DukeException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class DeleteCommand extends Command {
    int serialNumber;

    public DeleteCommand(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        if (this.serialNumber < 1 || this.serialNumber > taskList.size()) {
            throw new DukeException("Have you entered the correct number?");
        }
        String taskDescription = taskList.get(this.serialNumber - 1).description;
        taskList.delete(this.serialNumber);
        storage.writeToFile(taskList);
        ui.outputMessage("Noted. I've removed this task:\n" +
                taskDescription +
                "\nNow you have " + taskList.size() + " tasks in the list.");
    }
}
