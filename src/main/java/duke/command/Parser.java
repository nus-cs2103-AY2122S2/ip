package duke.command;

import duke.exception.DukeInvalidCommandException;
import duke.task.TaskType;

/**
 * Creates {@link Command} objects.
 * Translates command strings to an instance of some subclass of <code>Command</code>
 * that can subsequently be executed.
 */
public class Parser {
    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_CREATE_TODO = "todo";
    private static final String COMMAND_CREATE_DEADLINE = "deadline";
    private static final String COMMAND_CREATE_EVENT = "event";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_UPCOMING = "upcoming";
    private static final String COMMAND_BETWEEN = "between";
    private static final String COMMAND_SCHEDULE = "schedule";
    private static final String COMMAND_FIND = "find";

    /**
     * Creates a {@link Command} object based on the supplied command string.
     *
     * @param command Command string to be translated.
     * @return An instance of the associated <code>Command</code> object for handling the command.
     * @throws DukeInvalidCommandException If an invalid command string is supplied.
     */
    public static Command parse(String command) throws DukeInvalidCommandException {
        final String[] commandParts = command.split(" ");
        final String commandLowerCase = commandParts[0].toLowerCase();
        final String args = command.substring(commandLowerCase.length()).trim();

        return instantiateCommand(commandLowerCase, args);
    }

    /**
     * Matches the supplied command word to the appropriate type of Command.
     * @param commandWord The command word to identify the type of Command object to be created.
     * @param args The argument string to be passed to the created Command object.
     * @return A new instance of the Command class for the command word supplied.
     * @throws DukeInvalidCommandException If the command word is invalid.
     */
    private static Command instantiateCommand(String commandWord, String args)
            throws DukeInvalidCommandException {
        switch (commandWord) {
        case COMMAND_EXIT:
            return new ExitCommand(args);
        case COMMAND_LIST:
            return new ListCommand(args);
        case COMMAND_MARK:
            // Fallthrough
        case COMMAND_UNMARK:
            return new MarkCommand(args, commandWord.equals(COMMAND_MARK));
        case COMMAND_CREATE_TODO:
            return new CreateCommand(args, TaskType.TODO);
        case COMMAND_CREATE_DEADLINE:
            return new CreateCommand(args, TaskType.DEADLINE);
        case COMMAND_CREATE_EVENT:
            return new CreateCommand(args, TaskType.EVENT);
        case COMMAND_DELETE:
            return new DeleteCommand(args);
        case COMMAND_UPCOMING:
            return new UpcomingCommand(args);
        case COMMAND_SCHEDULE:
            return new ScheduleCommand(args);
        case COMMAND_BETWEEN:
            return new BetweenCommand(args);
        case COMMAND_FIND:
            return new FindCommand(args);
        default:
            throw new DukeInvalidCommandException(String.format("No such command: %s", commandWord));
        }
    }
}
