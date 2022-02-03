package duke.command;

import duke.exception.DukeIllegalArgumentException;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.Printable;

/**
 * Represents a handler for the mark and unmark commands.
 * Encapsulates all set completion logic.
 */
public class MarkCommand extends Command {
    private final boolean isDone;

    /**
     * Creates a handler for the mark or unmark command, depending on the supplied argument.
     *
     * @param args Arguments supplied to the command handler.
     * @param isDone The new completion state that the handler should set a task to.
     */
    MarkCommand(String args, boolean isDone) {
        super(args);
        this.isDone = isDone;
    }

    @Override
    public boolean execute(Printable linePrinter, TaskList taskList) throws DukeIllegalArgumentException {
        final Task task = parseSelectTask(taskList);

        if (task.isDone() == this.isDone) {
            linePrinter.print(String.format("Task is already %s:", this.isDone ? "done" : "not done"));
        } else if (this.isDone) {
            taskList.markTask(task, true);
            linePrinter.print("Great Job Finishing the task:");
        } else {
            taskList.markTask(task, false);
            linePrinter.print("Marking the task as not done yet:");
        }
        linePrinter.print(String.format("\t %s", task.getReadableString()));
        return true;
    }
}
