package baron.commands;

import baron.tasks.TaskManager;

public class ListCommand extends Command {
    private final TaskManager taskManager;

    public ListCommand(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public String execute() {
        return this.taskManager.toString();
    }
}
