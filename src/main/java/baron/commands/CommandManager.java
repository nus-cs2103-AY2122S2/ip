package baron.commands;

import baron.tasks.TaskManager;

public class CommandManager {
    private final TaskManager taskManager;

    public CommandManager(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    public Command parseCommand(String fullCommand) {
        fullCommand = fullCommand.strip();
        if (fullCommand.equals("bye")) {
            return new ByeCommand();
        } else if (fullCommand.equals("list")) {
            return new ListCommand(this.taskManager);
        } else {
            return new AddTaskCommand(this.taskManager, fullCommand);
        }
    }
}
