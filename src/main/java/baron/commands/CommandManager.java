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
     * @param storage     the {@code Storage} required in executing some commands.
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
        assert fullCommand != null : "fullCommand cannot be null";

        if (fullCommand == null) {
            return new EmptyCommand();
        }

        fullCommand = fullCommand.strip();
        if (fullCommand.equals("bye")) {
            return new ByeCommand();
        } else if (fullCommand.equals("list")) {
            return new ListCommand(this.taskManager);
        }

        String[] fullCommands = fullCommand.split("\\s+", 2);
        String commandArg = "";

        if (fullCommands.length == 0) {
            return new EmptyCommand();
        }

        assert (fullCommands.length == 2) || (fullCommands.length == 1) : "splitString.length should be 1 or 2 only";

        if (fullCommands.length == 2) {
            commandArg = fullCommands[1];
        }

        switch (fullCommands[0]) {
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
        case "find":
            return new FindCommand(this.taskManager, commandArg);
        default:
            return new InvalidCommand();
        }
    }
}
