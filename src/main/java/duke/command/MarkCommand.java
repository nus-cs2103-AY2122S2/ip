package duke.command;

import duke.exception.DukeIllegalArgumentException;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.Printable;

public class MarkCommand extends Command {
    private final boolean isDone;

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
