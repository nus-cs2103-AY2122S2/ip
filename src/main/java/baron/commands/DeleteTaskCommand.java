package baron.commands;

import baron.exceptions.BaronException;
import baron.messages.Messages;
import baron.tasks.Task;
import baron.tasks.TaskManager;

public class DeleteTaskCommand extends Command {
    private final TaskManager taskManager;
    private final String commandArgs;

    public DeleteTaskCommand(TaskManager taskManager, String commandArgs) {
        this.taskManager = taskManager;
        this.commandArgs = commandArgs;
    }

    @Override
    public String execute() {
        try {
            int index = CommandParser.parseTaskIntArg(this.commandArgs);
            Task deletedTask = this.taskManager.deleteTask(index);
            return Messages.MESSAGE_DELETE_SUCCESS + deletedTask.toString() + "\n"
                    + Messages.generateNoOfTasksMessage(this.taskManager.getTaskCount());
        } catch (BaronException e) {
            return e.toString();
        }
    }
}
