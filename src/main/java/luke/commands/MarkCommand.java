package luke.commands;

import luke.data.TaskList;

public class MarkCommand extends UpdateCommand {

    public static final CommandAction COMMAND_ACTION = CommandAction.MARK;
    private static final String DEFAULT_MESSAGE = "Using the force... Great! I have forced this task as done.";

    public MarkCommand(int index) {
        super(index);
    }

    @Override
    public CommandResult execute(TaskList taskList) {
        try {
            taskList.get(getIndex() - 1).markAsDone();
            return new CommandResult(DEFAULT_MESSAGE);
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult("The force cannot find the task.\nPlease try again :(");
        }
    }
}
