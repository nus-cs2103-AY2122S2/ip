package baron.commands;

import baron.exceptions.BaronException;
import baron.message.Message;
import baron.tasks.Task;
import baron.tasks.TaskManager;
import baron.util.Storage;

public class DeleteTaskCommand extends Command {
    private final TaskManager taskManager;
    private final String commandArgs;
    private final Storage storage;

    public DeleteTaskCommand(TaskManager taskManager, Storage storage, String commandArgs) {
        this.taskManager = taskManager;
        this.storage = storage;
        this.commandArgs = commandArgs;
    }

    @Override
    public String execute() {
        try {
            int index = CommandParser.parseTaskIntArg(this.commandArgs);
            Task deletedTask = this.taskManager.deleteTask(index);
            try {
                this.storage.save(this.taskManager.getAllTasks());
            } catch (BaronException e) {
                this.taskManager.revertChanges();
                throw e;
            }
            this.taskManager.commitChanges();
            return Message.MESSAGE_DELETE_SUCCESS + deletedTask.toString() + "\n"
                    + Message.generateNoOfTasksMessage(this.taskManager.getTaskCount());
        } catch (BaronException e) {
            return e.toString();
        }
    }
}
