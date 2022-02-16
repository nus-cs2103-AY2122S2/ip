package baron.commands;

import baron.exceptions.BaronException;
import baron.message.Message;
import baron.tasks.Task;
import baron.tasks.TaskManager;
import baron.tasks.TaskType;
import baron.util.Storage;

/**
 * Represents the add task command, denoted by todo, deadline and event.
 */
public class AddTaskCommand extends Command {
    private final TaskManager taskManager;
    private final Storage storage;
    private final String commandArg;
    private final TaskType taskType;

    /**
     * Constructs an add task command with the specified {@code TaskManager}, {@code Storage},
     * {@code TaskType} and command arguments.
     *
     * @param taskManager the {@code TaskManager} for the command execution.
     * @param storage the {@code Storage} for the command execution.
     * @param taskType the {@code TaskType} for the command execution.
     * @param commandArg the command arguments to be parsed.
     * @see TaskManager
     * @see Storage
     * @see TaskType
     */
    public AddTaskCommand(TaskManager taskManager, Storage storage, TaskType taskType, String commandArg) {
        this.taskManager = taskManager;
        this.storage = storage;
        this.commandArg = commandArg;
        this.taskType = taskType;
    }

    /**
     * Executes the add task command by adding the task to this {@code TaskManager} and saving it
     * using {@code Storage}.
     *
     * @return the add task command output.
     */
    public String execute() {
        if (this.commandArg.isBlank()) {
            return (new BaronException(Message.generateEmptyDescMessage(this.taskType))).toString();
        }

        try {
            Task addedTask = this.taskManager.addTask(this.taskType, this.commandArg);
            try {
                this.storage.save(this.taskManager.getAllTasks());
            } catch (BaronException e) {
                this.taskManager.revertChanges();
                throw e;
            }
            this.taskManager.commitChanges();
            return Message.MESSAGE_ADD_TASK_SUCCESS + addedTask.toString() + "\n"
                    + Message.generateNoOfTasksMessage(this.taskManager.getTaskCount());
        } catch (BaronException e) {
            return e.toString();
        }

    }
}
