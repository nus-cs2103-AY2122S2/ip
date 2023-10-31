package duke.commands;

import duke.admin.Storage;
import duke.admin.TaskList;
import duke.admin.Ui;
import duke.exceptions.DukeException;
import duke.tasks.Task;

/**
 * ChangeMarkCommand changes the marked status of a task, either by marking it or unmarking it.
 */
public class ChangeMarkCommand extends Command {
    private String description;
    private boolean isMarkRequest;

    /**
     * Constructor for ChangeMarkCommand
     * @param index index of task to be changed
     * @param isMarkRequest whether the command wants to mark the task or unmark the task
     */
    public ChangeMarkCommand(String description, boolean isMarkRequest) {
        this.description = description;
        this.isMarkRequest = isMarkRequest;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        int index = Integer.parseInt(description) - 1;

        if (index >= tasks.getNumberOfTasks() || index < 0) {
            throw new DukeException(DukeException.INVALID_FORMAT);
        }

        Task task = tasks.changeMark(index, isMarkRequest);
        storage.updateAfterChangeMark(index, isMarkRequest);

        return Ui.showChangeMarkMessage(task, isMarkRequest);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
