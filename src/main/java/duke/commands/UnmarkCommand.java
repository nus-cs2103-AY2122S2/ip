package duke.commands;

import duke.exceptions.DukeInvalidArgumentException;
import duke.tasks.Task;

public class UnmarkCommand extends Command {

    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute() throws DukeInvalidArgumentException {
        if (index > taskList.size() - 1 || index < 0) {
            throw new DukeInvalidArgumentException("I am afraid that's an invalid task!" +
                    " Please check your task number");
        }
        Task unmarkedTask = taskList.unmarkTask(index);
        return String.format("Very well. The following task has been marked as not done:" +
                "%s", unmarkedTask);
    }
}
