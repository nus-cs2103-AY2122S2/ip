package siri;

import java.io.IOException;

//DeleteCommand.java reused from Brigette Santoso E0564307
/**
 * Handles the deletion of a task from the list
 */
public class DeleteCommand extends Command {
    protected int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidInputException {
        Task task = tasks.getTasks().get(taskIndex);
        tasks.deleteTask(taskIndex);
        ui.showDeleteTask(task, tasks.getTasks());
        storage.save(tasks.getTasks());
    }
}
