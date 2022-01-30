package duke.logic;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.AddCommand;
import duke.command.ClearCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

public class Parser {
    private enum CommandType {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, CLEAR
    }

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public static int parseIndex(String index) {
        return Integer.parseInt(index) - 1;
    }

    public static String parseDescription(String options, String divider) {
        String[] splitCommand = options.split(divider);
        return splitCommand[0];
    }

    public static LocalDateTime parseTime(String options, String divider) throws DukeException {
        String[] splitCommand = options.split(divider);
        if (splitCommand.length < 2) {
            throw new DukeException("NO TIME SUPPLIED");
        }

        LocalDateTime time;

        try {
            time = LocalDateTime.parse(splitCommand[1], formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("INVALID TIME FORMAT (dd-MM-yyyy HH:mm)");
        }

        return time;
    }

    public static Command parse(String input) throws DukeException {
        String[] splitInput = input.split(" ", 2);

        CommandType commandType;
        Command command;

        try {
            commandType = CommandType.valueOf(splitInput[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DukeException("INVALID COMMAND");
        }

        try {
            switch (commandType) {
            case BYE:
                command = new ExitCommand();
                break;
            case LIST:
                command = new ListCommand();
                break;
            case MARK:
                command = new MarkCommand(parseIndex(splitInput[1]), true);
                break;
            case UNMARK:
                command = new MarkCommand(parseIndex(splitInput[1]), false);
                break;
            case DELETE:
                command = new DeleteCommand(parseIndex(splitInput[1]));
                break;
            case TODO:
                command = new AddCommand(new Todo(splitInput[1], false));
                break;
            case EVENT:
                command = new AddCommand(new Event(parseDescription(splitInput[1], " /at "),
                        false, parseTime(splitInput[1], " /at ")));
                break;
            case DEADLINE:
                command = new AddCommand(new Deadline(parseDescription(splitInput[1], " /by "),
                        false, parseTime(splitInput[1], " /by ")));
                break;
            case CLEAR:
                command = new ClearCommand();
                break;
            default:
                throw new DukeException("INVALID COMMAND");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("TOO FEW ARGUMENTS SUPPLIED");
        }

        return command;
    }
}
