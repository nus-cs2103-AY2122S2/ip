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
 * Represents a command.
 *
 * @author snoidetx
 * @version 0.0.0
 */
public interface Command {
    public String execute(UltoiUi ui, TaskList tasks, Storage storage) throws UltoiException;
}
