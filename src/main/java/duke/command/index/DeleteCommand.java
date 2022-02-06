package duke.command.index;

import duke.command.CommandFeedback;
import duke.command.CommandType;
import duke.exception.InvalidArgumentException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command to delete a task in the task list. A
 * <code>DeleteCommand</code> object records the index of a task input
 * by the user. When executing the object, the index task will be deleted
 * from the task list.
 */
public class DeleteCommand extends IndexCommand {
    public static final String COMMAND_WORD = "delete";
    public static final String COMMAND_FORMAT = COMMAND_WORD + COMMAND_ARG;

    /**
     * Creates an instance of a DeleteCommand object.
     *
     * @param index the index of the task in the task list.
     */
    public DeleteCommand(int index) {
        super(index);
    }

    /**
     * Returns a command feedback after the execution of the
     * DeleteCommand which deletes a task in the task list.
     *
     * @param taskList a list of task.
     * @return a command feedback of CommandType.DELETE.
     * @throws InvalidArgumentException if index of task is out of bounds.
     */
    @Override
    public CommandFeedback execute(TaskList taskList) throws InvalidArgumentException {
        try {
            int formerSize = taskList.size();

            Task task = taskList.get(index);
            taskList.remove(task);

            assert taskList.size() == formerSize - 1 : "deletion failed number of task does not add up.";

            return new CommandFeedback(CommandType.DELETE, taskList, task);

        } catch (IndexOutOfBoundsException e) {
            throw new InvalidArgumentException(COMMAND_FORMAT);
        }
    }

    /**
     * Returns the command word of the DeleteCommand.
     *
     * @return command word.
     */
    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }
}
