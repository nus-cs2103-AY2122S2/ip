package duke.command;

import duke.DukeException;
import duke.common.Messages;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.TextUi;

public class ExitCommand extends Command {
    private static final boolean IS_EXIT = true;

    public ExitCommand() {
        super(IS_EXIT);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        storage.saveAllTasks(tasks);
        return TextUi.showExecutionMessage(Messages.MESSAGE_GOOD_BYE);
    }
}
