package baron.commands;

import baron.exceptions.BaronException;
import baron.messages.Messages;
import baron.tasks.Task;
import baron.tasks.TaskManager;
import baron.tasks.TaskType;

public class AddTaskCommand extends Command {
    private final TaskManager taskManager;
    private final String commandArg;
    private final TaskType taskType;

    public AddTaskCommand(TaskManager taskManager, TaskType taskType, String commandArg) {
        this.taskManager = taskManager;
        this.commandArg = commandArg;
        this.taskType = taskType;
    }

    public String execute() {
        if (this.commandArg.isBlank()) {
            return (new BaronException(Messages.generateEmptyArgMessage(this.taskType))).toString();
        }

        Task addTaskOutput;
        try {
            addTaskOutput = this.taskManager.addTask(this.taskType, this.commandArg);
        } catch (BaronException e) {
            return e.toString();
        }
        return Messages.MESSAGE_ADD_TASK_SUCCESS + addTaskOutput.toString() + "\n"
                + Messages.generateNoOfTasksMessage(this.taskManager.getTaskCount());
    }
}
