package duke.parser;

import duke.commands.*;
import duke.exceptions.DukeInvalidArgumentException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Parses user inputs for commands.
 */
public class Parser {

    /**
     * Parses user input for specified commands.
     *
     * @param userInput raw input from user.
     * @return Command parsed from user input.
     * @throws DukeInvalidArgumentException If user input invalid arguments.
     */
    public Command parseCommands(String userInput) throws DukeInvalidArgumentException {
        String[] parsedUserInput = userInput.split(" ", 2);
        String commandType = parsedUserInput[0].toLowerCase();
        switch (commandType) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "sort":
                return new SortCommand();
            case "mark":
                return new MarkCommand(parseIndex(parsedUserInput[1]));
            case "unmark":
                return new UnmarkCommand(parseIndex(parsedUserInput[1]));
            case "delete":
                return new DeleteCommand(parseIndex(parsedUserInput[1]));
            case "find":
                return new FindCommand(parsedUserInput[1].trim());
            case "todo":
                String[] todoParsedArguments = parseArguments(parsedUserInput);
                String content = todoParsedArguments[0];
                return new AddCommand(commandType, content, null);
            case "deadline":
            case "event":
                String[] parsedArguments = parseArguments(parsedUserInput);
                content = parsedArguments[0];
                LocalDateTime datetime = parseDateTime(parsedArguments[1]);
                return new AddCommand(commandType, content, datetime);
            default:
                return new InvalidCommand();
        }
    }

    private int parseIndex(String strIndex) {
        return Integer.parseInt(strIndex.trim()) - 1;
    }

    private String[] parseArguments(String[] arguments) throws DukeInvalidArgumentException {
        if (arguments.length < 2) {
            throw new DukeInvalidArgumentException("There appears to be insufficient arguments");
        }
        return arguments[1].split(" /([Aa][Tt]|[Bb][Yy]) ", 2);
    }

    private LocalDateTime parseDateTime(String datetime) {
        DateTimeFormatter datetimePattern = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return LocalDateTime.parse(datetime, datetimePattern);
    }

    /**
     * Parses date and time from data in savefile.
     *
     * @param datetime date and time to be parsed.
     * @return LocalDateTime object based on given date time.
     */
    public LocalDateTime parseSaveDateTime(String datetime) {
        DateTimeFormatter datetimePattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(datetime, datetimePattern);
    }
    
    public Task parseSavedTask(String currSaveLine) {
        Task currentTask = null;
        switch (currSaveLine.charAt(0)) {
            case 'T' : {
                currentTask = Todo.createFromData(currSaveLine);
                break;
            }
            case 'E' : {
                currentTask = Event.createFromData(currSaveLine);
                break;
            }
            case 'D' : {
                currentTask = Deadline.createFromData(currSaveLine);
                break;
            }
        }
        return currentTask;
    }
}