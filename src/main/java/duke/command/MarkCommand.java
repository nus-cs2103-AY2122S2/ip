package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.exception.DukeException;

public class MarkCommand extends Command {
    protected int number;

    public MarkCommand(int number) {
        this.number = number - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.markTask(number);
        ui.showMarkTask(tasks.getTasks().get(number));
        storage.save(tasks.getTasks());
    }
}
