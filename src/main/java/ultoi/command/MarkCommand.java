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

public class MarkCommand implements Command {
    private static final int COMMAND_LENGTH_MARK = 4;
    private static final int COMMAND_LENGTH_UNMARK = 6;
    private static final String MESSAGE_MARK = "Nice! I have marked this task as done:";
    private static final String MESSAGE_UNMARK = "Okay! I have marked this task as not done yet:";

    private final boolean isMark;
    private final int index;

    public MarkCommand(String input) throws UltoiException {
        this.isMark = input.startsWith("mark");

        try {
            this.index = Integer.parseInt(input.substring(
                    (this.isMark ? this.COMMAND_LENGTH_MARK : this.COMMAND_LENGTH_UNMARK) + 1)) - 1;
        } catch (Exception e) {
            throw UltoiException.commandMismatchException();
        }
    }

    public void execute(UltoiUi ui, TaskList tasks, Storage storage) throws UltoiException {
        try {
            if (this.isMark) {
                tasks.mark(this.index);
            } else {
                tasks.unmark(this.index);
            }
        } catch (Exception e) {
            throw UltoiException.indexOutOfBoundException();
        }

        storage.save(tasks);
        ui.showMsg(generateMsg(tasks.getTask(this.index)));
        return;
    }

    private String generateMsg(Task task) {
        return (this.isMark ? this.MESSAGE_MARK : this.MESSAGE_UNMARK) + "\n"
                + UltoiUi.INDENT + task.toString();
    }
}
