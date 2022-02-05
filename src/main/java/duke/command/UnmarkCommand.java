package duke.command;

import duke.ui.Ui;
import duke.tasklist.TaskList;
import duke.storage.Storage;

public class UnmarkCommand extends Commands {
    public static final String COMMAND_WORDS = "unmark";
    public static final String SUCCESS_MESSAGE = "    Command Executed Successfully";
    public static final String FAILURE_MESSAGE = "    'Unmark' Command Executed Unsuccessfully";
    private static boolean IS_EXIT = false;
    private String arguments; // In the form of user duke.command

    public UnmarkCommand(String arguments) {
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
            if (tasks.marksTask(storage, (Integer.parseInt(trimmedArgument) - 1), false)) {
                return new CommandResult(SUCCESS_MESSAGE);
            }
        } catch (IndexOutOfBoundsException err) {
            System.out.println("    Unmarking of tasks unsuccessful due to: " + err);
            return new CommandResult(FAILURE_MESSAGE);
        }
        return new CommandResult(FAILURE_MESSAGE);
    }
}
