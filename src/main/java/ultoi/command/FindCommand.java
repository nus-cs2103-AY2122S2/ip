package ultoi.command;

import ultoi.task.Task;
import ultoi.task.ToDo;
import ultoi.task.Deadline;
import ultoi.task.Event;

import ultoi.util.Ultoi;
import ultoi.util.UltoiUi;
import ultoi.util.UltoiException;
import ultoi.util.Storage;
import ultoi.util.TaskList;
import ultoi.util.Parser;
import ultoi.util.DateTime;

public class FindCommand implements Command {
    private static final int COMMAND_LENGTH = 4;
    private static final String MESSAGE = "Got it! I have found the following matching tasks:";

    private final String keyword;

    public FindCommand(String input) throws UltoiException {
        try {
            this.keyword = input.substring(this.COMMAND_LENGTH + 1);
        } catch (Exception e) {
            throw UltoiException.commandMismatchException();
        }
    }

    public void execute(UltoiUi ui, TaskList tasks, Storage storage) throws UltoiException {
        ui.showMsg(generateMsg(tasks));
        return;
    }

    private String generateMsg(TaskList tasks) {
        return this.MESSAGE + "\n"
                + tasks.findTasksWith(this.keyword).toString();
    }
}
