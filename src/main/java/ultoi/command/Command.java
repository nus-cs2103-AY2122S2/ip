package ultoi.command;

import ultoi.util.Storage;
import ultoi.util.TaskList;
import ultoi.util.UltoiException;
import ultoi.util.UltoiUi;

/**
 * Represents a command.
 *
 * @author snoidetx
 * @version 0.0.0
 */
public interface Command {
    public String execute(UltoiUi ui, TaskList tasks, Storage storage) throws UltoiException;
}
