package command;

import exception.DukeException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int serialNumber) {
        this.index = serialNumber - 1;
    }

    /**
     * Mark Task in TaskList as done.
     * Also overwrite Storage.
     *
     * @param ui Ui for outputting message.
     * @param storage Storage for rewriting TaskList.
     * @param taskList TaskList that stores Tasks.
     * @throws DukeException If index number out of bounds or problems with writing to Storage.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        if (this.index < 0 || this.index >= taskList.size()) {
            throw new DukeException("Have you entered the correct number?");
        }
        taskList.get(this.index).setTaskStatus(true);
        storage.writeToFile(taskList);
        ui.outputMessage("Nice! I've marked this task as done: \n"
                + taskList.get(this.index));
    }
}
