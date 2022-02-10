package duke.command;

import duke.task.TaskList;
import duke.exception.InvalidTaskNumberException;

public class UnmarkCommand extends Command {
    private final int id;

    public UnmarkCommand(int id) {
        this.id = id;
    }

    @Override
    public String executeCommand(TaskList taskList) throws InvalidTaskNumberException {
        return taskList.mark(id, "unmark");
    }
}
