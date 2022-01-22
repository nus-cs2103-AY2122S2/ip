package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.exception.DukeException;
import duke.task.Task;

public class DeleteCommand extends Command{
    protected int number;

    public DeleteCommand(int number) {
        this.number = number - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.getTasks().get(number);
        tasks.deleteTask(number);
        ui.showDeleteTask(task, tasks.getTasks());
        storage.save(tasks.getTasks());
    }
}
