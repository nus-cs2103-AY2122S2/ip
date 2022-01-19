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
            String[] splitString = fullCommand.split("\\s+", 2);
            if (splitString.length == 2) {
                if (splitString[0].equals("mark")) {
                    return new MarkTaskCommand(this.taskManager, splitString[1]);
                } else if (splitString[0].equals("unmark")) {
                    return new UnmarkTaskCommand(this.taskManager, splitString[1]);
                } else {
                    return new AddTaskCommand(this.taskManager, fullCommand);
                }
            }
            return new AddTaskCommand(this.taskManager, fullCommand);
        }
    }
}
