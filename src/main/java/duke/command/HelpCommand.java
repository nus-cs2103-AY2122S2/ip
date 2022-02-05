package duke.command;

import duke.ui.Ui;
import duke.tasklist.TaskList;
import duke.storage.Storage;

/**
 * Represents a command that prompt user to enter a valid command. A <code>HelpCommand</code> object
 * corresponds to a command that prompts and educate the user valid commands of the bot.
 */
public class HelpCommand extends Commands {

    public static final String COMMAND_WORDS = "help";
    public static final String SUCCESS_MESSAGE = "    Command Executed Successfully";
    public static final String FAILURE_MESSAGE = "    'Help' Command Executed Unsuccessfully";
    private static boolean IS_EXIT = false;

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
     * Executes a <Code>HelpCommand</Code>. When the command is executed, the user is prompted
     * to type a valid command, and introduce them to valid commands of the bot.
     *
     * @param tasks An arraylist of tasks reflective of the current state in the database.
     * @param ui A class that controls the user-interface of the user.
     * @param storage A class that is in-charge of writing, appending, and reading the database.
     * @return A command result with a string reflective of the task's success.
     */
    @Override
    public CommandResult execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("    Did you accidentally write an incorrect duke.command.");
        return new CommandResult(SUCCESS_MESSAGE);
    }
}