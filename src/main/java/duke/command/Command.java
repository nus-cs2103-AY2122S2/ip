package duke.command;

import duke.exception.InvalidArgumentException;
import duke.task.TaskList;

public abstract class Command {
    public abstract CommandFeedback execute(TaskList taskList) throws InvalidArgumentException;
}
