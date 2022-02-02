package duke.command;

import duke.exception.DukeException;
import duke.exception.EmptyDescriptionException;
import duke.tasklist.TaskList;

/**
 * Represents a command to mark task as done.
 */
public class MarkCommand extends Command {
    private int taskIndex;

    /**
     * Creates a MarkCommand.
     *
     * @param taskPosition Position of task in the list of task.
     * @throws DukeException If taskPosition is empty or of invalid format.
     */
    public MarkCommand(String taskPosition) throws DukeException {
        super(taskPosition);

        if (taskPosition == "") {
            throw new EmptyDescriptionException("OOPS!!! The taskPosition cannot be empty.");
        }
        try {
            int taskNumber = Integer.valueOf(taskPosition);
            taskIndex = taskNumber - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Please input number after mark");
        }
    }

    /**
     * Execute MarkCommand to mark task as done in a TaskList.
     *
     * @param taskList The TaskList for command act on.
     * @throws DukeException If taskList does not contain the task.
     */
    @Override
    public void callCommand(TaskList taskList) throws DukeException {
        taskList.mark(this.taskIndex);
    }
}
