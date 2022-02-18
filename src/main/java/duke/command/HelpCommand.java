package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents a command that prompt user to enter a valid command. A ]HelpCommand object
 * corresponds to a command that prompts and educate the user valid commands of the bot.
 */
public class HelpCommand extends Commands {

    public static final String SUCCESS_MESSAGE = "Pika? Check if you have typed in the wrong command pikapika!";
    private static final boolean IS_EXIT = false;

    /**
     * Aid in exiting the program if the command calls for it.
     *
     * @return the apt exit instruction.
     */
    @Override
    public boolean isExit() {
        return IS_EXIT;
    }

    /**
     * Executes a HelpCommand. When the command is executed, the user is prompted
     * to type a valid command, and introduce them to valid commands of the bot.
     *
     * @param tasks An arraylist of tasks reflective of the current state in the database.
     * @param ui A class that controls the user-interface of the user.
     * @param storage A class that is in-charge of writing, appending, and reading the database.
     * @return A command result with a string reflective of the task's success.
     */
    @Override
    public CommandResult execute(TaskList tasks, Ui ui, Storage storage) {
        return new CommandResult(SUCCESS_MESSAGE);
    }
}
