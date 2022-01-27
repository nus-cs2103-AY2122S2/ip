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

public class ByeCommand implements Command {
    private static final String COMMAND_BYE = "bye";
    private static final String MESSAGE = "Bye. See you. <O_O>";

    public ByeCommand(String input) throws UltoiException {
        if (! input.equals(COMMAND_BYE)) {
            throw UltoiException.commandMismatchException();
        }
    }

    @Override
    public void execute(UltoiUi ui, TaskList tasks, Storage storage) {
        ui.showMsg(this.MESSAGE);
        return;
    }
}
