package command;

import ui.Ui;
import tasklist.TaskList;
import storage.Storage;

public class ListCommand extends Commands {

    public static final String COMMAND_WORDS = "list";
    public static final String SUCCESS_MESSAGE = "    Command Executed Successfully";
    public static final String FAILURE_MESSAGE = "    'List' Command Executed Unsuccessfully";
    private static boolean IS_EXIT = false;

    @Override
    public boolean isExit() {
        return IS_EXIT;
    }

    @Override
    public CommandResult execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.printFileContent() == Boolean.TRUE) {
            return new CommandResult(SUCCESS_MESSAGE);
        } else {
            System.out.println("    Failed to print database content");
        }
        return new CommandResult(FAILURE_MESSAGE);
    }
}
