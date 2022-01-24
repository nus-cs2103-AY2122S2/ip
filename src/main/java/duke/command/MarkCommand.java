package duke.command;

import duke.TaskList;
import duke.exception.DukeIllegalArgumentException;
import duke.task.Task;
import duke.util.IPrintable;

public class MarkCommand extends Command {
    private final boolean newState;
    public MarkCommand(String args, boolean newState) {
        super(args);
        this.newState = newState;
    }

    @Override
    public boolean execute(IPrintable linePrinter, TaskList taskList) throws DukeIllegalArgumentException {
        Task task = parseSelectTask(taskList);

        if (task.isDone() == newState) {
            linePrinter.print(String.format("Task is already %s:", newState ? "done" : "not done"));
        } else if (newState) {
            task.markAsDone();
            taskList.notifyListeners();
            linePrinter.print("Great Job Finishing the task:");
        } else {
            task.unmarkAsDone();
            taskList.notifyListeners();
            linePrinter.print("Marking the task as not done yet:");
        }
        linePrinter.print(String.format("\t %s", task.getReadableString()));
        return true;
    }
}
