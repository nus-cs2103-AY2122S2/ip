package duke.command;

import duke.ui.Ui;
import duke.tasklist.TaskList;

import duke.storage.Storage;

public class MarkCommand extends Commands {
    public static final String COMMAND_WORDS = "mark";
    public static final String SUCCESS_MESSAGE = "    Command Executed Successfully";
    public static final String FAILURE_MESSAGE = "    'Mark' Command Executed Unsuccessfully";
    private static boolean IS_EXIT = false;
    private String arguments; // In the form of user duke.command

    public MarkCommand(String arguments) {
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
            if (tasks.marksTask(storage, (Integer.parseInt(trimmedArgument) - 1), true)) {
                return new CommandResult(SUCCESS_MESSAGE);
            }
        } catch (IndexOutOfBoundsException err) {
            System.out.println("    Marking of tasks unsuccessful due to: " + err);
            return new CommandResult(FAILURE_MESSAGE);
        }
        return new CommandResult(FAILURE_MESSAGE);
    }
}
