package duke.command;

import duke.exception.DukeIllegalArgumentException;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.IPrintable;

/**
 * Represents a handler for the mark and unmark commands.
 * Encapsulates all set completion logic.
 */
public class MarkCommand extends Command {
    private final boolean newState;

    /**
     * Creates a handler for the mark or unmark command, depending on the supplied argument.
     * @param args Arguments supplied to the command handler.
     * @param newState The new completion state that the handler should set a task to.
     */
    MarkCommand(String args, boolean newState) {
        super(args);
        this.newState = newState;
    }

    @Override
    public boolean execute(IPrintable linePrinter, TaskList taskList) throws DukeIllegalArgumentException {
        Task task = parseSelectTask(taskList);

        if (task.isDone() == newState) {
            linePrinter.print(String.format("Task is already %s:", newState ? "done" : "not done"));
        } else if (newState) {
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
