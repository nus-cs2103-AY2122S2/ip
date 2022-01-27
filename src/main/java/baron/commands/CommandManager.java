package baron.commands;

import baron.tasks.TaskManager;
import baron.tasks.TaskType;
import baron.util.Storage;

/**
 * Parses the given command string and returns the correct {@code Command} for execution,
 * together with the required dependencies ({@code TaskManager} or {@code Storage}).
 */
public class CommandManager {
    private final TaskManager taskManager;
    private final Storage storage;

    /**
     * Constructs a {@code CommandManager} object with the specified {@code TaskManager}
     * and {@code Storage}.
     *
     * @param taskManager the {@code TaskManager} required in executing some commands.
     * @param storage the {@code Storage} required in executing some commands.
     */
    public CommandManager(TaskManager taskManager, Storage storage) {
        this.taskManager = taskManager;
        this.storage = storage;
    }

    /**
     * Parses the given full command string and returns a {@code Command}.
     *
     * @param fullCommand the full command string to be parsed.
     * @return a {@code Command} object ready for execution.
     */
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
                return new MarkTaskCommand(this.taskManager, this.storage, commandArg);
            case "unmark":
                return new UnmarkTaskCommand(this.taskManager, this.storage, commandArg);
            case "todo":
                return new AddTaskCommand(this.taskManager, this.storage, TaskType.TODO, commandArg);
            case "deadline":
                return new AddTaskCommand(this.taskManager, this.storage, TaskType.DEADLINE, commandArg);
            case "event":
                return new AddTaskCommand(this.taskManager, this.storage, TaskType.EVENT, commandArg);
            case "delete":
                return new DeleteTaskCommand(this.taskManager, this.storage, commandArg);
            default:
                return new InvalidCommand();
            }
        }
    }
}
