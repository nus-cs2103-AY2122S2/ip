package duke.command;

import duke.function.Storage;
import duke.function.TaskList;
import duke.function.Ui;

/**
 * Represents a command to list out all current tasks.
 */
public class ListCommand extends Command {
    /**
     * If tags should be printed.
     */
    private boolean printTags;
    /**
     * Initializes the help command with the user input.
     *
     * @param fullCommand The user input.
     */
    public ListCommand(String fullCommand) {
        super(fullCommand);
        String[] fullCommandSplit = fullCommand.split(" ");
        if (fullCommandSplit.length > 1 && fullCommandSplit[1].equals("--tags")) {
            this.printTags = true;
        } else {
            this.printTags = false;
        }
    }

    /**
     * Prints out a list of all current tasks.
     *
     * @param tasks   The current tasks for the command to interact with.
     * @param ui      The ui for the command to print output.
     * @param storage The storage for the command to save and load tasks to an external file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (printTags) {
            return tasks.toStringTags(ui);
        } else {
            return tasks.toString(ui);
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
