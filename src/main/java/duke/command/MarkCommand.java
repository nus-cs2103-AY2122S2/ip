package duke.command;

import duke.exception.InvalidArgumentException;
import duke.task.Task;
import duke.task.TaskList;

public class MarkCommand extends IndexCommand {
    public static final String COMMAND_WORD_MARK = "mark";
    public static final String COMMAND_WORD_UNMARK = "unmark";
    private static final String COMMAND_FORMAT_MARK = COMMAND_WORD_MARK + COMMAND_ARG;
    private static final String COMMAND_FORMAT_UNMARK = COMMAND_WORD_UNMARK + COMMAND_ARG;

    private final boolean isMark;

    public static String getFormat(boolean isMark) {
        return (isMark ? COMMAND_FORMAT_MARK : COMMAND_FORMAT_UNMARK);
    }

    public MarkCommand(int index, boolean isMark) {
        super(index);
        this.isMark = isMark;
    }

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
}
