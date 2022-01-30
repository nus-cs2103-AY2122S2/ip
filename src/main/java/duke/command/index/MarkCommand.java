package duke.command.index;

import duke.command.CommandFeedback;
import duke.command.CommandType;
import duke.exception.InvalidArgumentException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command to mark/unmark a task in the task list. A
 * <code>MarkCommand</code> object records mark or unmark status of a task
 * input by the user. When executing the object, the task will be mark/unmark
 * depending on the status provided.
 */
public class MarkCommand extends IndexCommand {
    public static final String COMMAND_WORD_MARK = "mark";
    public static final String COMMAND_WORD_UNMARK = "unmark";
    private static final String COMMAND_FORMAT_MARK = COMMAND_WORD_MARK + COMMAND_ARG;
    private static final String COMMAND_FORMAT_UNMARK = COMMAND_WORD_UNMARK + COMMAND_ARG;

    private final boolean isMark;

    /**
     * Creates an instance of a MarkCommand object.
     *
     * @param index the index of the task in the task list.
     * @param isMark whether the command is mark or unmark.
     */
    public MarkCommand(int index, boolean isMark) {
        super(index);
        this.isMark = isMark;
    }

    /**
     * Returns the command format of a mark/unmark command.
     * @param isMark whether the command is mark or unmark.
     * @return mark/unmark command format.
     */
    public static String getFormat(boolean isMark) {
        return (isMark ? COMMAND_FORMAT_MARK : COMMAND_FORMAT_UNMARK);
    }



    /**
     * Returns a command feedback after the execution of the
     * MarkCommand which mark/unmark a task in the task list based
     * on whether it is a mark/unmark command.
     *
     * @param taskList a list of task.
     * @return a command feedback of CommandType.MARK/UNMARK.
     * @throws InvalidArgumentException if index of task is out of bounds.
     */
    @Override
    public CommandFeedback execute(TaskList taskList) throws InvalidArgumentException {
        try {
            Task task = taskList.get(index);

            if (isMark) {
                task.markTask();

                return new CommandFeedback(CommandType.MARK, task);

            } else {
                task.unmarkTask();

                return new CommandFeedback(CommandType.UNMARK, task);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidArgumentException(getFormat(isMark));
        }
    }

    /**
     * Returns the command word of the MarkCommand based on whether the
     * command is mark or unmark.
     *
     * @return mark/unmark command word.
     */
    @Override
    public String getCommandWord() {
        return (isMark ? COMMAND_WORD_MARK : COMMAND_WORD_UNMARK);
    }
}
