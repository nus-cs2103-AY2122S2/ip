package duke.command;

import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;

import duke.exception.InvalidIndexException;

import duke.task.Task;

import java.io.IOException;

/**
 * A representation of the command for deleting task.
 */
public class DeleteCommand extends Command {
    String commandArgument;

    /**
     * Class constructor.
     *
     * @param commandArgument command argument from user input
     */
    public DeleteCommand(String commandArgument) {
        this.commandArgument = commandArgument;
    }

    /**
     * Executes deleting command
     * @param tasks   TaskList class
     * @param ui      Ui class
     * @param storage Storage class
     * @throws InvalidIndexException if the index is invalid
     * @throws IOException if file not found
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidIndexException, IOException {
        int index = Integer.parseInt(commandArgument) - 1;
        if (index < 0 || index > tasks.getNumberOfTasks() - 1) {
            throw new InvalidIndexException();
        }

        Task deletedTask = tasks.getTaskByIndex(index);
        tasks.removeTaskByIndex(index);

        storage.writeTaskToFile(tasks);
        return ui.printConfirmDelete(deletedTask, tasks.getNumberOfTasks());

    }
}
