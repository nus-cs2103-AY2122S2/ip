package command;

import ui.Ui;
import tasklist.TaskList;
import storage.Storage;

public class HelpCommand extends Commands {

    public static final String COMMAND_WORDS = "help";
    public static final String SUCCESS_MESSAGE = "    Command Executed Successfully";
    public static final String FAILURE_MESSAGE = "    'Help' Command Executed Unsuccessfully";
    private static boolean IS_EXIT = false;

    @Override
    public boolean isExit() {
        return IS_EXIT;
    }

    @Override
    public CommandResult execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("    Did you accidentally write an incorrect command.");
        return new CommandResult(SUCCESS_MESSAGE);
    }
}