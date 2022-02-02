package Duke.Commands;

import Duke.DukeException.DukeException;
import Duke.System.Storage;
import Duke.System.TaskList;
import Duke.System.Ui;
import Duke.Tasks.Task;
import Duke.Tasks.TaskToDo;

public class CommandToDo extends Command {
    private final String name;

    public CommandToDo(String name) {
        this.name = name;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = new TaskToDo(this.name);
        tasks.add(task);
        storage.save(tasks);
        ui.showTaskAdded();
    }
}
