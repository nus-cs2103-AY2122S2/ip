package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;

public class ExitCommand extends Command {
    private final String userName;

    public ExitCommand(String userName) {
        this.userName = userName;
    }

    @Override
    public TaskList execute(TaskList taskList, Ui ui, Storage storage) {
        System.out.println(String.format("Bye %s. See you again soon!", this.userName));
        return taskList;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
