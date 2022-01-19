package baron.commands;

import baron.exceptions.BaronException;
import baron.messages.Messages;
import baron.tasks.TaskManager;

public class UnmarkTaskCommand extends Command {
    private final TaskManager taskManager;
    private final String commandArgs;

    public UnmarkTaskCommand(TaskManager taskManager, String commandArgs) {
        this.taskManager = taskManager;
        this.commandArgs = commandArgs;
    }

    @Override
    public String execute() {
        int index;
        try {
            index = CommandParser.parseIntArg(this.commandArgs);
            this.taskManager.unmarkTask(index);
            return Messages.MESSAGE_UNMARK_SUCCESS + this.taskManager.getTask(index).toString();
        } catch (BaronException e) {
            return e.toString();
        }
    }
}
