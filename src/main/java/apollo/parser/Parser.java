package apollo.parser;

import static apollo.messages.Messages.INSUFFICIENT_ARGUMENTS;
import static apollo.messages.Messages.INVALID_DATETIME_FORMAT;
import static apollo.messages.Messages.INVALID_INDEX;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import apollo.commands.AddCommand;
import apollo.commands.Command;
import apollo.commands.DeleteCommand;
import apollo.commands.ExitCommand;
import apollo.commands.FindCommand;
import apollo.commands.InvalidCommand;
import apollo.commands.ListCommand;
import apollo.commands.MarkCommand;
import apollo.exceptions.ApolloIllegalArgumentException;
import apollo.tasks.Task;

/**
 * Parses user inputs for commands.
 */
public class Parser {

    private static final String PATTERN = "dd-MM-yyyy HH:mm";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);

    /**
     * Parses supplied arguments for date and time.
     * Returns in {@code LocalDateTime} type.
     *
     * @param args To parse for date and time.
     * @return Date and time.
     * @throws ApolloIllegalArgumentException If args in wrong format to parse date and time.
     */
    private LocalDateTime parseDateTime(String[] args) throws ApolloIllegalArgumentException {
        try {
            String dateTimeString = parseArgs(args)[1].trim();
            return LocalDateTime.parse(dateTimeString, FORMATTER);
        } catch (DateTimeParseException | IndexOutOfBoundsException e) {
            throw new ApolloIllegalArgumentException(
                    INVALID_DATETIME_FORMAT + PATTERN);
        }
    }

    /**
     * Parses arguments for description until "/at" or "/by".
     *
     * @param args To parse for description.
     * @return String array containing description and other arguments.
     * @throws ApolloIllegalArgumentException If not enough arguments are supplied.
     */
    private String[] parseArgs(String[] args) throws ApolloIllegalArgumentException {
        if (args.length < 2) {
            throw new ApolloIllegalArgumentException(INSUFFICIENT_ARGUMENTS);
        }

        return args[1].split(" */(at|by) *", 2);
    }

    /**
     * Parses arguments for 1-based index.
     * Returns as 0-based index.
     *
     * @param args To parse for index.
     * @return Index parsed as an Integer.
     * @throws ApolloIllegalArgumentException If invalid Integer is supplied.
     */
    private int parseIndex(String[] args) throws ApolloIllegalArgumentException {
        try {
            return Integer.parseInt(parseArgs(args)[0].trim()) - 1;
        } catch (NumberFormatException e) {
            throw new ApolloIllegalArgumentException(INVALID_INDEX);
        }
    }

    /**
     * Parses user input for recognised commands.
     *
     * @param userInput String of full user input.
     * @return Command object.
     * @throws ApolloIllegalArgumentException If user input contains invalid information.
     */
    public Command parseCommand(String userInput)
            throws ApolloIllegalArgumentException {
        String[] args = userInput.trim().split(" ", 2);
        String command = args[0].toLowerCase();

        switch (command) {
        case "todo":
            String description = parseArgs(args)[0].trim();
            return new AddCommand(description,
                    null,
                    Task.Type.valueOf(command.toUpperCase()));
        case "deadline": case "event":
            description = parseArgs(args)[0].trim();
            LocalDateTime dateTime = parseDateTime(args);
            return new AddCommand(description,
                    dateTime,
                    Task.Type.valueOf(command.toUpperCase()));
        case "mark": case "unmark":
            return new MarkCommand(parseIndex(args), command.equals("mark"));
        case "delete":
            return new DeleteCommand(parseIndex(args));
        case "list":
            return new ListCommand();
        case "exit":
            return new ExitCommand();
        case "find":
            description = parseArgs(args)[0].trim();
            return new FindCommand(description);
        default:
            return new InvalidCommand();
        }
    }
}
