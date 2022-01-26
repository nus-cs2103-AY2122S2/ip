package luke.commands;

import luke.data.TaskList;
import luke.data.tasks.Task;

public class DeleteCommand extends UpdateCommand {

    private static final String DEFAULT_MESSAGE = "Forcing it out... Success! I've removed the following task:\n\t%s";
    public static final CommandAction COMMAND_ACTION = CommandAction.DELETE;

    public DeleteCommand(int index) {
        super(index);
    }

    @Override
    public CommandResult execute(TaskList taskList) {
        try {
            Task removedTask = taskList.remove(getIndex() - 1);
            return new CommandResult(String.format(DEFAULT_MESSAGE, removedTask));
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult("The force cannot find the task.\nPlease try again :(");
        }
    }
}
