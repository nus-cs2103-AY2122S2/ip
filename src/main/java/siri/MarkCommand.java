package siri;

import java.io.IOException;

//MarkCommand.java reused from Brigette Santoso E0564307
/**
 * Handles the marking of a single task inside the list
 */
public class MarkCommand extends Command {
    protected int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidInputException {
        tasks.markTask(taskIndex);
        ui.showMarkTask(tasks.getTasks().get(taskIndex));
        storage.save(tasks.getTasks());
    }
}
