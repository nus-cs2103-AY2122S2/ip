package duke.commands;

import duke.managers.TaskList;
import duke.managers.Ui;
import duke.managers.Storage;
import duke.tasks.Task;
import duke.exceptions.DukeException;

public class DeleteCommand extends Command {
    protected int index;
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui io, Storage storage) throws DukeException {
        taskList.remove(index);
        if(index >= taskList.getSize() || index < 0)
            throw new DukeException("Invalid input! This task number does not exist.");
        Task task = taskList.remove(index);
        io.showMessage("Noted. I've removed this task:\n       "
                + task.toString()
                + "\n     Now you have " + taskList.getSize() + " task(s) in the list.");
        storage.save(taskList);
    }
}
