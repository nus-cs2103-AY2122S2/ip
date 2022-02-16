package duke.commands;

import duke.exceptions.DukeInvalidArgumentException;
import duke.tasks.Task;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute() throws DukeInvalidArgumentException {
        if (index > taskList.size() || index < 0) {
            throw new DukeInvalidArgumentException("I am afraid that's an invalid task!" +
                    " Please check your task number");
        }
        Task markedTask = taskList.markTask(index);
        return String.format("""
                Duly noted. The following task has been marked as done
                %s""", markedTask, taskList.size());

    }

}
