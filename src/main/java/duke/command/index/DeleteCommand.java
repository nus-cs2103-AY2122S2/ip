package duke.command.index;

import duke.command.CommandFeedback;
import duke.command.CommandType;
import duke.exception.InvalidArgumentException;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand extends IndexCommand {
    public static final String COMMAND_WORD = "delete";
    public static final String COMMAND_FORMAT = COMMAND_WORD + COMMAND_ARG;

    public DeleteCommand(int index) {
        super(index);
    }

    @Override
    public CommandFeedback execute(TaskList taskList) throws InvalidArgumentException {
        try {
            Task task = taskList.get(index);
            taskList.remove(task);

            return new CommandFeedback(CommandType.DELETE, taskList, task);

        } catch (IndexOutOfBoundsException e) {
            throw new InvalidArgumentException(COMMAND_FORMAT);
        }
    }

    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }
}
