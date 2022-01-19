package baron.commands;

import baron.tasks.Task;
import baron.tasks.TaskManager;

public class AddTaskCommand extends Command {
    private final TaskManager taskManager;
    private final String taskToBeAdded;

    public AddTaskCommand(TaskManager taskManager, String taskToBeAdded) {
        this.taskManager = taskManager;
        this.taskToBeAdded = taskToBeAdded;
    }

    public String execute() {
        String addTaskOutput = this.taskManager.addTask(new Task(this.taskToBeAdded));
        return "added: " + addTaskOutput;
    }
}
