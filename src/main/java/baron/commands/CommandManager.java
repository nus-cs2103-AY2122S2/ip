package baron.commands;

import baron.tasks.TaskManager;
import baron.tasks.TaskType;
import baron.util.StorageManager;

public class CommandManager {
    private final TaskManager taskManager;
    private final StorageManager storageManager;

    public CommandManager(TaskManager taskManager, StorageManager storageManager) {
        this.taskManager = taskManager;
        this.storageManager = storageManager;
    }

    public Command parseCommand(String fullCommand) {
        fullCommand = fullCommand.strip();
        if (fullCommand.equals("bye")) {
            return new ByeCommand();
        } else if (fullCommand.equals("list")) {
            return new ListCommand(this.taskManager);
        } else {
            String[] splitString = fullCommand.split("\\s+", 2);
            String commandArg = "";

            if (splitString.length == 0) {
                return new EmptyCommand();
            }

            // splitString is of length 1 or 2
            if (splitString.length == 2) {
                commandArg = splitString[1];
            }

            switch (splitString[0]) {
                case "mark":
                    return new MarkTaskCommand(this.taskManager, this.storageManager, commandArg);
                case "unmark":
                    return new UnmarkTaskCommand(this.taskManager, this.storageManager, commandArg);
                case "todo":
                    return new AddTaskCommand(this.taskManager, this.storageManager, TaskType.TODO, commandArg);
                case "deadline":
                    return new AddTaskCommand(this.taskManager, this.storageManager, TaskType.DEADLINE, commandArg);
                case "event":
                    return new AddTaskCommand(this.taskManager, this.storageManager, TaskType.EVENT, commandArg);
                case "delete":
                    return new DeleteTaskCommand(this.taskManager, this.storageManager, commandArg);
                default:
                    return new InvalidCommand();
            }
        }
    }
}
