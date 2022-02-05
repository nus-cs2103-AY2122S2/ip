package duke.command;

import duke.ui.Ui;
import duke.tasklist.TaskList;
import duke.storage.Storage;

public class DeleteCommand extends Commands {
    public static final String COMMAND_WORDS = "delete";
    public static final String SUCCESS_MESSAGE = "    Command Executed Successfully";
    public static final String FAILURE_MESSAGE = "    'Delete' Command Executed Unsuccessfully";

    private static boolean IS_EXIT = false;
    private String arguments; // In the form of user duke.command

    public DeleteCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public boolean isExit() {
        return IS_EXIT;
    }

    @Override
    public CommandResult execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String trimmedArgument = arguments.trim();
            tasks.deletesTask((Integer.parseInt(trimmedArgument) - 1), storage);
            return new CommandResult(SUCCESS_MESSAGE);
        } catch (IndexOutOfBoundsException err) {
            System.out.println("    Deleting of tasks unsuccessful due to: " + err);
        }
        return new CommandResult(FAILURE_MESSAGE);
    }
}
