package baron.commands;

import baron.exceptions.BaronException;
import baron.messages.Messages;
import baron.tasks.TaskManager;

public class MarkTaskCommand extends Command {
    private final TaskManager taskManager;
    private final String commandArgs;

    public MarkTaskCommand(TaskManager taskManager, String commandArgs) {
        this.taskManager = taskManager;
        this.commandArgs = commandArgs;
    }

    @Override
    public String execute() {
        int index;
        try {
            index = CommandParser.parseIntArg(this.commandArgs);
            this.taskManager.markTask(index);
            return Messages.MESSAGE_MARK_SUCCESS + this.taskManager.getTask(index).toString();
        } catch (BaronException e) {
            return e.toString();
        }
    }
}
