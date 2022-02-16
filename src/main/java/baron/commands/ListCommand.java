package baron.commands;

import baron.message.Message;
import baron.tasks.TaskManager;

/**
 * Represents the list command that list all the tasks.
 */
public class ListCommand extends Command {
    private final TaskManager taskManager;

    /**
     * Constructs the {@code ListCommand} object with the specified {@code TaskManager}.
     *
     * @param taskManager the {@code TaskManager} where all the tasks reside.
     */
    public ListCommand(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    /**
     * Returns the list of all the tasks in {@code TaskManager}.
     *
     * @return the list of all the tasks in {@code TaskManager}.
     */
    @Override
    public String execute() {
        if (this.taskManager.getTaskCount() == 0) {
            return Message.MESSAGE_NO_TASK;
        }
        return Message.MESSAGE_LIST_TASK + this.taskManager.toString();
    }
}
