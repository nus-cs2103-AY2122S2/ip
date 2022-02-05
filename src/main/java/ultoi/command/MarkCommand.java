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

/**
 * Represents a command that marks a task as done or undone.
 *
 * @author snoidetx
 * @version 0.0.0
 */
public class MarkCommand implements Command {
    private static final int COMMAND_LENGTH_MARK = 4;
    private static final int COMMAND_LENGTH_UNMARK = 6;
    private static final String MESSAGE_MARK = "Nice! I have marked this task as done:";
    private static final String MESSAGE_UNMARK = "Okay! I have marked this task as not done yet:";

    private final boolean isMark;
    private final int index;

    /**
     * Creates an mark command.
     *
     * @param input User input.
     * @throws UltoiException If the input cannot be identified.
     */
    public MarkCommand(String input) throws UltoiException {
        this.isMark = input.startsWith("mark");

        try {
            this.index = Integer.parseInt(input.substring(
                    (this.isMark ? this.COMMAND_LENGTH_MARK : this.COMMAND_LENGTH_UNMARK) + 1)) - 1;
        } catch (Exception e) {
            throw UltoiException.commandMismatchException();
        }
    }

    /**
     * Executes the command.
     *
     * @param ui User interface to be used.
     * @param tasks Task list to be used.
     * @param storage Storage to be used.
     * @throws UltoiException If any Ultoi exception happens.
     */
    public String execute(UltoiUi ui, TaskList tasks, Storage storage) throws UltoiException {
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
        return ui.showMsg(generateMsg(tasks.getTask(this.index)));
    }

    /**
     * Generates a message for the command.
     *
     * @param task Task to be marked.
     * @return Message for the command.
     */
    private String generateMsg(Task task) {
        return (this.isMark ? this.MESSAGE_MARK : this.MESSAGE_UNMARK) + "\n"
                + UltoiUi.INDENT + task.toString();
    }
}
