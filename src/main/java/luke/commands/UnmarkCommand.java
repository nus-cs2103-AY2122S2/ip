package luke.commands;

import luke.data.TaskList;

public class UnmarkCommand extends UpdateCommand {

    private static final String DEFAULT_MESSAGE = "Force should be used for greater good!\nI've forced this task as not done yet...";
    public static final CommandAction COMMAND_ACTION = CommandAction.UNMARK;

    public UnmarkCommand(int index) {
        super(index);
    }

    @Override
    public CommandResult execute(TaskList taskList) {
        try {
            taskList.get(getIndex() - 1).unmarkAsDone();
            return new CommandResult(DEFAULT_MESSAGE);
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult("The force cannot find the task.\nPlease try again :(");
        }
    }
}
