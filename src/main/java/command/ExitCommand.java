package command;

import ui.Ui;
import tasklist.TaskList;
import storage.Storage;

public class ExitCommand extends Commands {

    public static final String COMMAND_WORDS = "bye";
    public static final String SUCCESS_MESSAGE = "    Command Executed Successfully";
    public static final String FAILURE_MESSAGE = "    'Bye' Command Executed Unsuccessfully";
    private static boolean IS_EXIT = true;

    @Override
    public boolean isExit() {
        System.out.println("    Hope to see you again soon :(");
        System.out.println("    Let's play video game the next time!");
        return IS_EXIT;
    }

    @Override
    public CommandResult execute(TaskList tasks, Ui ui, Storage storage) {
        return new CommandResult(SUCCESS_MESSAGE);
    }
}
