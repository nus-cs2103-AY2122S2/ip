package duke.command;

import duke.task.Tasks;
import duke.ui.Ui;
import duke.tasklist.TaskList;
import duke.storage.Storage;

import java.util.ArrayList;

public class FindCommand extends Commands {

    public static final String COMMAND_WORDS = "find";
    public static final String SUCCESS_MESSAGE = "    Command Executed Successfully";
    public static final String FAILURE_MESSAGE = "    'Find' Command Executed Unsuccessfully";

    private static final boolean IS_EXIT = false;
    private final String arguments;
    public FindCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public boolean isExit() {
        return IS_EXIT;
    }

    @Override
    public CommandResult execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println(tasks.queryTasks(arguments));
        return new CommandResult(SUCCESS_MESSAGE);
    }
}
