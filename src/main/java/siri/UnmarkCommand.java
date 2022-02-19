package siri;

import java.io.IOException;

public class UnmarkCommand extends Command {
    protected int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidInputException {
        tasks.unmarkTask(taskIndex);
        ui.showUnmarkTask(tasks.getTasks().get(taskIndex));
        storage.save(tasks.getTasks());
    }
}
