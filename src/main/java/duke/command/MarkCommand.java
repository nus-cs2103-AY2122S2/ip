package duke.command;

import duke.exception.DukeException;
import duke.function.Storage;
import duke.function.TaskList;
import duke.function.Ui;
import duke.task.Task;

/**
 * Represents a command to mark or unmark a task.
 */
public class MarkCommand extends Command {
    /**
     * To determine whether the command will mark or unmark the task.
     */
    private boolean isMark;
    /**
     * The specified task to be marked or unmarked.
     */
    private int taskNumber;
    /**
     * To store any exceptions that were thrown during the parsing of the command.
     */
    private DukeException exception;

    /**
     * Initializes a new mark command.
     *
     * @param fullCommand
     */
    public MarkCommand(String fullCommand) {
        super(fullCommand);
        String[] fullCommandSplit = fullCommand.split(" ");
        String command = fullCommandSplit[0];
        if (command.equals("mark")) {
            this.isMark = true;
        } else if (command.equals("unmark")) {
            this.isMark = false;
        }

        try {
            this.taskNumber = Integer.parseInt(fullCommandSplit[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            this.exception = new DukeException("Please input an item number when deleting (eg. 'mark 1')");
        } catch (NumberFormatException e) {
            this.exception = new DukeException("Please only input integers when deleting tasks (eg. 'mark 1");
        }

    }

    /**
     * Marks or unmarks the specified task as done.
     *
     * @param tasks   The current tasks for the command to interact with.
     * @param ui      The ui for the command to print output.
     * @param storage The storage for the command to save and load tasks to an external file.
     * @throws DukeException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.exception != null) {
            throw this.exception;
        } else if (this.taskNumber <= 0 || this.taskNumber > tasks.getSize()) {
            throw new DukeException("Please only input integers within the range of your tasks");
        }

        Task task = tasks.getByNumber(this.taskNumber);
        String output = "";
        if (this.isMark) {
            task.setMarked(true);
            output += ui.printReturn(String.format("I've marked task %d as done!", this.taskNumber));
        } else {
            task.setMarked(false);
            output += ui.printReturn(String.format("I've marked task %d as undone!", this.taskNumber));
        }
        output += ui.printReturn(String.format("  %d. %s", this.taskNumber, task.toString()));

        return output;
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
