package duke.command;

import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;

import duke.exception.InvalidIndexException;

import duke.task.Task;

public class DeleteCommand extends Command {
    String commandArgument;
    public DeleteCommand(String commandArgument) {
        this.commandArgument = commandArgument;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        int index = Integer.parseInt(commandArgument) - 1;
        if (index < 0 || index > tasks.getNumberOfTasks() - 1) {
            throw new InvalidIndexException();
        }

        Task deletedTask = tasks.getTaskByIndex(index);
        tasks.removeTaskByIndex(index);

        ui.printConfirmDelete(deletedTask, tasks);
        storage.writeTaskToFile(tasks);

    }
}
