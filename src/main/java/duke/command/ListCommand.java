package duke.command;

import duke.exception.DukeException;
import duke.function.TaskList;
import duke.function.Storage;
import duke.function.Ui;
import duke.task.Task;

/**
 * Represents a command to list out all current tasks.
 */
public class ListCommand extends Command {
    /**
     * Initializes the help command with the user input.
     *
     * @param fullCommand The user input.
     */
    public ListCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Prints out a list of all current tasks.
     *
     * @param tasks   The current tasks for the command to interact with.
     * @param ui      The ui for the command to print output.
     * @param storage The storage for the command to save and load tasks to an external file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.getSize() == 0) {
            System.out.println("You currently do not have any tasks *quack*, please add some more");
        } else {
            ui.print("These are your tasks *quack*:");
            for (int i = 1; i <= tasks.getSize(); i++) {
                Task task = tasks.getByNumber(i);
                ui.print(String.format("%d. %s", i, task.toString()));
            }
        }
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
