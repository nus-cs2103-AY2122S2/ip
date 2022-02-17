package duke.commands;

import duke.exceptions.DukeInvalidArgumentException;
import duke.tasks.Task;

public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute() throws DukeInvalidArgumentException {
        if (index > taskList.size() - 1 || index < 0) {
            throw new DukeInvalidArgumentException("I am afraid that's an invalid task!" +
                                               "Please check your task number");
        }
        Task deletedTask = taskList.deleteTask(index);
        return String.format("As you wish. The following task has been removed:\n" +
                "%s\n" + "You now have %d item(s) in your list", deletedTask, taskList.size());
    }
}
