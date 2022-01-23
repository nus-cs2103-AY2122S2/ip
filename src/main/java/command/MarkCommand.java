package command;

import exception.DukeException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class MarkCommand extends Command {
    int index;

    public MarkCommand(int serialNumber) {
        this.index = serialNumber - 1;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        if (this.index < 0 || this.index >= taskList.size()) {
            throw new DukeException("Have you entered the correct number?");
        }
        taskList.get(this.index).isDone = true;
        storage.writeToFile(taskList);
        ui.outputMessage("Nice! I've marked this task as done: \n" +
                taskList.get(this.index));
    }
}
