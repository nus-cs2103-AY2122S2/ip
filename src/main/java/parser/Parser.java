package parser;

import java.time.LocalDate;
import java.util.Arrays;

import bot.BotException;
import commands.AddCommand;
import commands.Command;
import commands.DeleteCommand;
import commands.ExitCommand;
import commands.FindCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.UnmarkCommand;
import tasks.Deadline;
import tasks.Event;
import tasks.Todo;

/**
 * Represents a parser to process the inputs to a chat bot.
 */
public class Parser {
    private static final BotException UNSUPPORTED_COMMAND_EXCEPTION =
            new BotException("I'm sorry, but I don't know what that means :-(");

    /**
     * Returns a command to be executed if the query is a recognised command.
     * Otherwise, a `BotException` would be thrown.
     *
     * @param query the input to be parsed.
     * @return The command recognised from the query to be executed.
     * @throws BotException If the command from the query is unrecognised or
     * syntactically invalid.
     */
    public Command parse(String query) throws BotException {
        final String[] tokens = query.split(" ", 2);
        final String command = tokens[0];
        final String[] args = Arrays.copyOfRange(tokens, 1, tokens.length);

        switch (command) {
        case ListCommand.COMMAND:
            return this.listAction(args);
        case FindCommand.COMMAND:
            return this.findAction(args);
        case MarkCommand.COMMAND:
            return this.markAction(args);
        case UnmarkCommand.COMMAND:
            return this.unmarkAction(args);
        case AddCommand.ADD_TODO_COMMAND:
            return this.addTodoAction(args);
        case AddCommand.ADD_DEADLINE_COMMAND:
            return this.addDeadlineAction(args);
        case AddCommand.ADD_EVENT_COMMAND:
            return this.addEventAction(args);
        case DeleteCommand.COMMAND:
            return this.deleteAction(args);
        case ExitCommand.COMMAND:
            return this.exitAction(args);
        default:
            throw Parser.UNSUPPORTED_COMMAND_EXCEPTION;
        }
    }

    private Command listAction(String[] args) {
        return new ListCommand();
    }

    private Command findAction(String[] args) throws BotException {
        if (args.length < 1) {
            throw new BotException("Please enter a non-empty keyword to find.");
        }

        final String[] tokens = args[0].split(" ");
        if (tokens.length < 1 || tokens[0].trim().isEmpty()) {
            throw new BotException("Please enter a non-empty keyword to find.");
        }
        if (tokens.length > 1) {
            throw new BotException("Please enter only one keyword to find.");
        }

        return new FindCommand(tokens[0].trim());
    }

    private Command markAction(String[] args) {
        final int idToMark = this.parseStringToIndex(args[0]);
        return new MarkCommand(idToMark);
    }

    private Command unmarkAction(String[] args) {
        final int idToUnmark = this.parseStringToIndex(args[0]);
        return new UnmarkCommand(idToUnmark);
    }

    private Command addTodoAction(String[] args) throws BotException {
        final String description = args[0].trim();
        if (description.isEmpty()) {
            throw new BotException("The description of a task cannot be empty.");
        }
        return new AddCommand(new Todo(description));
    }

    private Command addDeadlineAction(String[] args) throws BotException {
        final String[] deadlineArgs = args[0].split(" /by ");

        final String description = deadlineArgs[0].trim();
        if (description.isEmpty()) {
            throw new BotException("The description of a task cannot be empty.");
        }
        final LocalDate by = LocalDate.parse(deadlineArgs[1].trim(), Deadline.DATE_INPUT_FORMAT);

        return new AddCommand(new Deadline(description, by));
    }

    private Command addEventAction(String[] args) throws BotException {
        final String[] eventArgs = args[0].split(" /at ");

        final String description = eventArgs[0].trim();
        if (description.isEmpty()) {
            throw new BotException("The description of a task cannot be empty.");
        }
        final String at = eventArgs[1].trim();

        return new AddCommand(new Event(description, at));
    }

    private Command deleteAction(String[] args) {
        final int idToDelete = this.parseStringToIndex(args[0]);
        return new DeleteCommand(idToDelete);
    }

    private Command exitAction(String[] args) {
        return new ExitCommand();
    }

    private int parseStringToIndex(String str) {
        return Integer.parseInt(str) - 1;
    }
}
