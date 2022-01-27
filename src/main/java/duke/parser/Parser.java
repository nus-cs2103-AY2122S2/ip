package duke.parser;

import duke.*;
import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Responsible for parsing user input and identifying correctly formatted commands.
 */
public class Parser {

    /**
     * Parses and identifies the command provided by the user.
     * @param strCommand The user's command in String format.
     * @return The Command object corresponding to the user's input.
     * @throws DukeException If the user provides an invalid or incorrectly formatted command.
     */
    public static Command parse(String strCommand) throws DukeException {
        String[] splitCommand = strCommand.split(" ", 2);
        String keyword = splitCommand[0];
        Command command;

        switch (keyword) {
            case "bye":
                command = new ExitCommand();

                break;
            case "list":
                command = new ListCommand();

                break;
            case "mark":
                if (splitCommand.length == 1 || splitCommand[1].isBlank()) {
                    throw new MissingArgumentException("Missing task number");
                } else {
                    try {
                        int index = Integer.parseInt(splitCommand[1]);
                        command = new MarkCommand(index);
                    } catch (NumberFormatException e) {
                        throw new InvalidArgumentException("Not a valid number");
                    }
                }
                break;
            case "unmark":
                if (splitCommand.length == 1 || splitCommand[1].isBlank()) {
                    throw new MissingArgumentException("Missing task number");
                } else {
                    try {
                        int index = Integer.parseInt(splitCommand[1]);
                        command = new UnmarkCommand(index);
                    } catch (NumberFormatException e) {
                        throw new InvalidArgumentException("Not a valid number");
                    }
                }
                break;
            case "delete":
                if (splitCommand.length == 1 || splitCommand[1].isBlank()) {
                    throw new MissingArgumentException("Missing task number");
                } else {
                    try {
                        int index = Integer.parseInt(splitCommand[1]);
                        command = new DeleteCommand(index);
                    } catch (NumberFormatException e) {
                        throw new InvalidArgumentException("Not a valid number");
                    }
                }
                break;
            case "todo":
                if (splitCommand.length == 1 || splitCommand[1].isBlank()) {
                    throw new MissingArgumentException("Missing task number");
                } else {
                    String title = splitCommand[1];
                    command = new AddCommand(new Todo(title));
                }
                break;
            case "event":
                if (splitCommand.length == 1 || splitCommand[1].isBlank()) {
                    throw new MissingArgumentException("Missing name and time");
                } else {
                    String[] taskArguments = splitCommand[1].split("/at", 2);
                    if (taskArguments[0].isBlank()) {
                        throw new MissingArgumentException("Missing name");
                    } else if (taskArguments.length == 1 || taskArguments[1].isBlank()) {
                        throw new MissingArgumentException("Missing time");
                    } else {
                        String title = taskArguments[0];
                        String time = taskArguments[1];
                        try {
                            SimpleDateFormat parser = new SimpleDateFormat("d/M/yy HH:mm");
                            Date dateTime = parser.parse(time);
                            command = new AddCommand(new Event(title, dateTime));
                        } catch (ParseException e) {
                            throw new InvalidArgumentException("Incorrect time format");
                        }
                    }
                }
                break;
            case "deadline":
                if (splitCommand.length == 1 || splitCommand[1].isBlank()) {
                    throw new MissingArgumentException("Missing name and time");
                } else {
                    String[] taskArguments = splitCommand[1].split("/by", 2);
                    if (taskArguments[0].isBlank()) {
                        throw new MissingArgumentException("Missing name");
                    } else if (taskArguments.length == 1 || taskArguments[1].isBlank()) {
                        throw new MissingArgumentException("Missing time");
                    } else {
                        String title = taskArguments[0];
                        String time = taskArguments[1];
                        try {
                            SimpleDateFormat parser = new SimpleDateFormat("d/M/yy HH:mm");
                            Date dateTime = parser.parse(time);
                            command = new AddCommand(new Deadline(title, dateTime));
                        } catch (ParseException e) {
                            throw new InvalidArgumentException("Incorrect time format");
                        }
                    }
                }
                break;
            default:
                throw new InvalidCommandException("Not an understood command");
        }
        return command;
    }
}
