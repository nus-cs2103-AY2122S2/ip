package baron.commands;

import baron.exceptions.BaronException;
import baron.messages.Messages;
import baron.tasks.TaskManager;
import baron.util.StorageManager;

public class MarkTaskCommand extends Command {
    private final TaskManager taskManager;
    private final String commandArgs;
    private final StorageManager storageManager;

    public MarkTaskCommand(TaskManager taskManager, StorageManager storageManager, String commandArgs) {
        this.taskManager = taskManager;
        this.storageManager = storageManager;
        this.commandArgs = commandArgs;
    }

    @Override
    public String execute() {
        int index;
        try {
            index = CommandParser.parseTaskIntArg(this.commandArgs);
            if (this.taskManager.markTask(index)) {
                try {
                    this.storageManager.save(this.taskManager.getAllTasks());
                } catch (BaronException e) {
                    this.taskManager.unmarkTask(index);
                    throw e;
                }
            }
            return Messages.MESSAGE_MARK_SUCCESS + this.taskManager.getTask(index).toString();
        } catch (BaronException e) {
            return e.toString();
        }
    }
}
