package duke.command;

import duke.exception.DukeIllegalArgumentException;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.IPrintable;

public class DeleteCommand extends Command {
    DeleteCommand(String args) {
        super(args);
    }

    @Override
    public boolean execute(IPrintable linePrinter, TaskList taskList) throws DukeIllegalArgumentException {
        int taskIndex = parseTaskNumber();
        Task deleted = taskList.deleteTask(taskIndex);

        if (deleted == null) {
            throw new DukeIllegalArgumentException("No matching task with given number");
        }

        linePrinter.print("Deleted the task:");
        linePrinter.print(String.format("\t %s", deleted.getReadableString()));
        return true;
    }
}
