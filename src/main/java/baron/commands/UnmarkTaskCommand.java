package baron.commands;

import baron.exceptions.BaronException;
import baron.message.Message;
import baron.tasks.TaskManager;
import baron.util.Storage;

public class UnmarkTaskCommand extends Command {
    private final TaskManager taskManager;
    private final Storage storage;
    private final String commandArgs;

    public UnmarkTaskCommand(TaskManager taskManager, Storage storage, String commandArgs) {
        this.taskManager = taskManager;
        this.storage = storage;
        this.commandArgs = commandArgs;
    }

    @Override
    public String execute() {
        int index;
        try {
            index = CommandParser.parseTaskIntArg(this.commandArgs);
            if (this.taskManager.unmarkTask(index)) {
                try {
                    this.storage.save(this.taskManager.getAllTasks());
                } catch (BaronException e) {
                    this.taskManager.markTask(index);
                    throw e;
                }
            }
            return Message.MESSAGE_UNMARK_SUCCESS + this.taskManager.getTask(index).toString();
        } catch (BaronException e) {
            return e.toString();
        }
    }
}
