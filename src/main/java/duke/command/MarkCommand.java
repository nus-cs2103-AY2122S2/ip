package duke.command;

import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;

import duke.exception.InvalidIndexException;

import duke.task.Task;

import java.io.IOException;

/**
 * A representation of the command for marking a task.
 */
public class MarkCommand extends Command {
    String commandArgument;

    /**
     * Class constructor.
     *
     * @param commandArgument command argument from user input
     */
    public MarkCommand(String commandArgument) {
        this.commandArgument = commandArgument;
    }

    /**
     * Executes marking command.
     *
     * @param tasks   TaskList class
     * @param ui      Ui class
     * @param storage Storage class
     * @throws InvalidIndexException if the index is invalid
     * @throws IOException if file not found
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidIndexException, IOException {
        int index = Integer.parseInt(commandArgument) - 1;
        if (index < 0 || index > tasks.getNumberOfTasks() - 1) {
            throw new InvalidIndexException();
        }
        Task currentTask = tasks.getTaskByIndex(index);
        currentTask.markAsDone();

        ui.printConfirmMark(currentTask);
        storage.writeTaskToFile(tasks);

    }
}
