package command;

import exception.DukeException;
import storage.Storage;
import task.TaskList;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int serialNumber) {
        this.index = serialNumber - 1;
    }

    /**
     * Mark Task in TaskList as not done.
     * Also overwrite Storage.
     *
     * @param storage Storage for rewriting TaskList.
     * @param taskList TaskList that stores Tasks.
     * @throws DukeException If index number out of bounds or problems with writing to Storage.
     */
    @Override
    public String execute(Storage storage, TaskList taskList) throws DukeException {
        if (this.index < 0 || this.index >= taskList.size()) {
            throw new DukeException("Have you entered the correct number?");
        }

        assert (this.index >= 0 && this.index < taskList.size());
        taskList.get(this.index).setTaskStatus(false);
        storage.writeToFile(taskList);
        return "HOLD IT!\nI've marked this task as not done: \n"
                + taskList.get(this.index);
    }
}
