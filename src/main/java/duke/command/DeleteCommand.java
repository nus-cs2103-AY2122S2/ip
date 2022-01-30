package duke.command;

import duke.exception.DukeException;
import duke.function.Storage;
import duke.function.TaskList;
import duke.function.Ui;
import duke.task.Task;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    /**
     * The task number to be deleted.
     */
    private int taskNumber;
    /**
     * To store any exceptions that were thrown during the parsing of the command.
     */
    private DukeException exception;

    /**
     * Initializes the delete command with the provided user input.
     * Parses through the user input to retrieve the task number.
     *
     * @param fullCommand The user input.
     */
    public DeleteCommand(String fullCommand) {
        super(fullCommand);

        String[] fullCommandSplit = fullCommand.split(" ");
        try {
            this.taskNumber = Integer.parseInt(fullCommandSplit[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            this.exception = new DukeException("Please input an item number when deleting (eg. 'delete 1')");
        } catch (NumberFormatException e) {
            this.exception = new DukeException("Please only input integers when deleting tasks (eg. 'delete 1");
        }
    }

    /**
     * Deletes the task corresponding to the specified number.
     *
     * @param tasks   The current tasks for the command to interact with.
     * @param ui      The ui for the command to print output.
     * @param storage The storage for the command to save and load tasks to an external file.
     * @throws DukeException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.exception != null) {
            throw this.exception;
        } else if (this.taskNumber <= 0 || this.taskNumber > tasks.getSize()) {
            throw new DukeException("Please only input integers within the range of your tasks");
        }

        Task removedTask = tasks.deleteByNumber(this.taskNumber);
        ui.print(String.format("I've deleted task %d! *quack*", this.taskNumber));
        ui.print(String.format("  %s", removedTask.toString()));
    }

    /**
     * Returns false as this is not an exit command.
     *
     * @return
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
