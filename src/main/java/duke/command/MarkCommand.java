package duke.command;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents a command used to mark the completion of a <code>Tasks</code> object.
 * A <code>MarkCommand</code> object corresponds to a command represented by the
 * input arguments from the user e.g., <code>"1"</code>
 */
public class MarkCommand extends Commands {
    public static final String COMMAND_WORDS = "mark";
    public static final String SUCCESS_MESSAGE = "";
    public static final String FAILURE_MESSAGE = "";
    private static final boolean IS_EXIT = false;
    private final String arguments; // In the form of user duke.command

    public MarkCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Returns the apt exit instruction after the command is executed.
     * If the program ends after this is executed, true is returned.
     *
     * @return the apt exit instruction.
     */
    @Override
    public boolean isExit() {
        return IS_EXIT;
    }

    /**
     * Executes the completion of a <code>Tasks</code> object. If the edit is successful,
     * a <code>CommandResult</code> containing a success message is returned, else
     * one containing a failure message is returned.
     *
     * @param tasks An arraylist of tasks reflective of the current state in the database.
     * @param ui A class that controls the user-interface of the user.
     * @param storage A class that is in-charge of writing, appending, and reading the database.
     * @return A command result with a string reflective of the task's success.
     */
    @Override
    public CommandResult execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String trimmedArgument = Parser.trim(arguments);
            return new CommandResult(tasks.marksTask(storage, Parser.convertBases(
                    Parser.parseToInt(trimmedArgument)), true));
        } catch (IndexOutOfBoundsException err) {
            return new CommandResult("Pika, marking the PokeTask was unsuccessful...\n"
                    + err);
        }
    }
}
