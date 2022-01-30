package apollo.parser;

import apollo.commands.*;
import apollo.exceptions.ApolloIllegalArgumentException;
import apollo.tasks.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    private static final String PATTERN = "dd-MM-yyyy HH:mm";
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN);

    private LocalDateTime parseDateTime(String[] args) throws ApolloIllegalArgumentException {
        try {
            String dateTimeString = parseArgs(args)[1].trim();
            return LocalDateTime.parse(dateTimeString, formatter);
        } catch (DateTimeParseException | IndexOutOfBoundsException e) {
            throw new ApolloIllegalArgumentException("Please add date and time in this format: " + PATTERN);
        }
    }

    private String[] parseArgs(String[] args) throws ApolloIllegalArgumentException {
        if (args.length < 2) {
            throw new ApolloIllegalArgumentException("Insufficient Arguments. ");
        }
        return args[1].split(" */(at|by) *", 2);
    }

    private int parseIndex(String[] args) throws ApolloIllegalArgumentException {
        try {
            return Integer.parseInt(parseArgs(args)[0].trim()) - 1;
        } catch (NumberFormatException e) {
            throw new ApolloIllegalArgumentException("Invalid index entered. ");
        }
    }

    public Command parseCommand(String userInput) throws ApolloIllegalArgumentException {
        String[] args = userInput.trim().split(" ", 2);
        String command = args[0].toLowerCase();
        String description;
        switch (command) {
        case "todo":
            description = parseArgs(args)[0].trim();
            return new AddCommand(description, null, Task.Type.valueOf(command.toUpperCase()));
        case "deadline": case "event":
            description = parseArgs(args)[0].trim();
            LocalDateTime dateTime = parseDateTime(args);
            return new AddCommand(description, dateTime, Task.Type.valueOf(command.toUpperCase()));
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
