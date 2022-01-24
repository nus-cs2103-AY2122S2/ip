package baron.commands;

import baron.exceptions.BaronException;
import baron.messages.Messages;
import baron.tasks.Task;
import baron.tasks.TaskManager;
import baron.tasks.TaskType;
import baron.util.StorageManager;

public class AddTaskCommand extends Command {
    private final TaskManager taskManager;
    private final StorageManager storageManager;
    private final String commandArg;
    private final TaskType taskType;

    public AddTaskCommand(TaskManager taskManager, StorageManager storageManager, TaskType taskType, String commandArg) {
        this.taskManager = taskManager;
        this.storageManager = storageManager;
        this.commandArg = commandArg;
        this.taskType = taskType;
    }

    public String execute() {
        if (this.commandArg.isBlank()) {
            return (new BaronException(Messages.generateEmptyDescMessage(this.taskType))).toString();
        }

        try {
            Task addedTask = this.taskManager.addTask(this.taskType, this.commandArg);
            try {
                this.storageManager.save(this.taskManager.getAllTasks());
            } catch (BaronException e) {
                this.taskManager.revertChanges();
                throw e;
            }
            this.taskManager.commitChanges();
            return Messages.MESSAGE_ADD_TASK_SUCCESS + addedTask.toString() + "\n"
                    + Messages.generateNoOfTasksMessage(this.taskManager.getTaskCount());
        } catch (BaronException e) {
            return e.toString();
        }

    }
}
