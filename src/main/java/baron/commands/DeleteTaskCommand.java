package baron.commands;

import baron.exceptions.BaronException;
import baron.message.Message;
import baron.tasks.Task;
import baron.tasks.TaskManager;
import baron.tasks.TaskType;
import baron.util.Storage;

/**
 * Represents a delete task command.
 */
public class DeleteTaskCommand extends Command {
    private final TaskManager taskManager;
    private final String commandArg;
    private final Storage storage;

    /**
     * Constructs a delete task command with the specified {@code TaskManager}, {@code Storage}
     * and command argument.
     *
     * @param taskManager the {@code TaskManager} for the command execution.
     * @param storage the {@code Storage} for the command execution.
     * @param commandArg the command argument to be parsed.
     * @see TaskManager
     * @see Storage
     */
    public DeleteTaskCommand(TaskManager taskManager, Storage storage, String commandArg) {
        this.taskManager = taskManager;
        this.storage = storage;
        this.commandArg = commandArg;
    }

    /**
     * Execute the delete task command by deleting the task from the specified {@code TaskManager},
     * and updating the {@code Storage} if necessary.
     *
     * @return the delete task command output.
     */
    @Override
    public String execute() {
        try {
            int index = CommandParser.parseTaskIntArg(this.commandArg);
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
