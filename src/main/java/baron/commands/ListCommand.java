package baron.commands;

import baron.message.Message;
import baron.tasks.TaskManager;

public class ListCommand extends Command {
    private final TaskManager taskManager;

    public ListCommand(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public String execute() {
        return Message.MESSAGE_LIST_TASK + this.taskManager.toString();
    }
}
