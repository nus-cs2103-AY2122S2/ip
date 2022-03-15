package duke.commands;

import duke.tasks.TaskList;
import duke.exceptions.DukeException;

/**
 * Interface representing a Command.
 */
public interface Command {
    String execute(TaskList taskList) throws DukeException;
}
