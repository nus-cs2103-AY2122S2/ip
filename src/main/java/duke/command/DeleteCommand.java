package duke.command;

import duke.exception.DukeException;
import duke.exception.EmptyDescriptionException;
import duke.tasklist.TaskList;

/**
 * Represents a command to delete task.
 */
public class DeleteCommand extends Command {
    private int taskIndex;

    /**
     * Creates a DeleteCommand.
     *
     * @param description Task to be deleted.
     * @throws DukeException If description is empty or is of an invalid format.
     */
    public DeleteCommand(String description) throws DukeException {
        super(description);

        if (description == "") {
            throw new EmptyDescriptionException("OOPS!!! The description cannot be empty.");
        }
        try {
            int taskNumber = Integer.valueOf(description);
            taskIndex = taskNumber - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Please input number after delete");
        }
    }

    /**
     * Executes DeleteCommand to delete task from a TaskList.
     *
     * @param taskList TaskList for command to delete task from.
     * @throws DukeException If taskList does not contain task to be deleted.
     * @return a String when command is called.
     */
    @Override
    public String callCommand(TaskList taskList) throws DukeException {
        return taskList.deleteTask(this.taskIndex);
    }
}
