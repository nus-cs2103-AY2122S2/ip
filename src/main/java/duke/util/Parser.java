package duke.util;

import duke.Commands.HelpCommand;
import duke.Commands.ByeCommand;
import duke.Commands.DeadlineCommand;
import duke.Commands.FindCommand;
import duke.Commands.EventCommand;
import duke.Commands.InvalidCommand;
import duke.Commands.ListCommand;
import duke.Commands.MarkCommand;
import duke.Commands.UnmarkCommand;
import duke.Commands.ToDoCommand;
import duke.Commands.ViewScheduleCommand;
import duke.Commands.DeleteCommand;
import duke.Commands.DukeCommand;

import duke.Exceptions.DukeException;
import duke.Exceptions.EmptyDescriptionException;
import duke.Exceptions.InvalidMessageException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * Parser is a class that makes sense of the user input
 */
public class Parser {

    /**
     * Determines and returns the command from the full user input
     * @param userInput The full user input
     * @return A DukeCommand object, indicating the command that is to be executed
     * @throws DukeException
     */
    public static DukeCommand parse(String userInput) throws DukeException {
        String userInputParts[] = userInput.split(" ");
        String command = userInputParts[0];
        try {
            if (command.equals("list")) {
                return new ListCommand("list");
            } else if (command.equals("todo")) {
                Parser.exceptionCheck(userInputParts.length, command);
                assert userInputParts.length == 1 : "Not handling empty body exception correctly";
                return new ToDoCommand(userInput.substring(5));
            } else if (command.equals("deadline")) {
                Parser.exceptionCheck(userInputParts.length, command);
                assert userInputParts.length == 1 : "Not handling empty body exception correctly";
                return new DeadlineCommand(userInput.substring(9));
            } else if (command.equals("event")) {
                Parser.exceptionCheck(userInputParts.length, command);
                assert userInputParts.length == 1 : "Not handling empty body exception correctly";
                return new EventCommand(userInput.substring(6));
            } else if (command.equals("mark")) {
                Parser.exceptionCheck(userInputParts.length, command);
                assert userInputParts.length == 1 : "Not handling empty body exception correctly";
                return new MarkCommand(userInput.substring(5));
            } else if (command.equals("unmark")) {
                Parser.exceptionCheck(userInputParts.length, command);
                assert userInputParts.length == 1 : "Not handling empty body exception correctly";
                return new UnmarkCommand(userInput.substring(7));
            } else if (command.equals("delete")) {
                Parser.exceptionCheck(userInputParts.length, command);
                assert userInputParts.length == 1 : "Not handling empty body exception correctly";
                return new DeleteCommand(userInput.substring(7));
            } else if (command.equals("find")) {
                Parser.exceptionCheck(userInputParts.length, command);
                assert userInputParts.length == 1 : "Not handling empty body exception correctly";
                return new FindCommand(userInput.substring(5));
            } else if (command.equals("viewSchedule")) {
              Parser.exceptionCheck(userInputParts.length, command);
              assert userInputParts.length == 1 : "Not handling empty body exception correctly";
              return new ViewScheduleCommand(userInput.substring(13));
            } else if (command.equals("help")) {
                return new HelpCommand();
            } else if (command.equals("bye")) {
                return new ByeCommand("bye");
            } else {
                throw new InvalidMessageException();
            }
        } catch (DukeException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    /**
     * Checks if the user enters a command without the appropriate description
     * @param size Length of the full user inputted command
     * @param command User inputted String
     * @throws DukeException
     */
    public static void exceptionCheck(int size, String command) throws DukeException {
        if (size == 1) {
            throw new EmptyDescriptionException(command);
        }
    }

    /**
     * Separates and returns the description of the task
     * @param commandBody The user inputted text
     * @return String description of the task
     */
    public static String parseDescription(String commandBody) {
       return commandBody.split("/")[0];
    }

    /**
     * Generates and returns a LocalDateTime object from the user inputted description
     * @param commandBody The parsed user inputted text
     * @param task The task by which a LocalDateTime should be generated for
     * @return LocalDateTime
     * @throws DukeException
     */
    public static LocalDateTime parseDateTime(String commandBody, String task) throws DukeException {
        String splitDesc[] = new String[0];

        if (task.equals("deadline")) {
            if (!commandBody.contains("/by")) {
                throw new DukeException("Please enter a valid date and time in the format: /by YYYY-MM-DD HHMM");
            }
            splitDesc = commandBody.split("/by");
        } else if (task.equals("event")) {
            if (!commandBody.contains("/at")) {
                throw new DukeException("Please enter a valid date and time in the format: /by YYYY-MM-DD HHMM");
            }
            splitDesc = commandBody.split("/at");
        }
        if (splitDesc.length == 1) {
            throw new DukeException("Please enter a valid date and time in the format: /by YYYY-MM-DD HHMM");
        }

        String dateTime[] = splitDesc[1].split(" ");

        if (dateTime.length == 0 || dateTime.length == 1) {
            throw new DukeException("Please enter a valid date and time in the format: /by YYYY-MM-DD HHMM");
        } else if (dateTime.length == 2) {
            throw new DukeException("Please enter a time");
        }
        return Parser.createDateTime(dateTime[1], dateTime[2]);
    }

    /**
     * Creates the localDateTime object
     * @param date String date
     * @param time String time
     * @return LocalDateTime object from the given date and time
     */
    public static LocalDateTime createDateTime(String date, String time) throws DukeException {
        LocalTime localTime;
        LocalDate deadline;

        try {
            deadline = Parser.parseDate(date);
            localTime = Parser.parseTime(time);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter a valid date in the format yyyy-mm-dd");
        } catch (DateTimeException e) {
            throw new DukeException("Please enter a valid time");
        }
        return LocalDateTime.of(deadline, localTime);
    }

    public static LocalDate parseDate(String date) throws DukeException {
        if (date.length() != 10) {
            throw new DukeException("Please enter a valid date now");
        }
        LocalDate deadline = LocalDate.parse(date);
        return deadline;
    }

    public static LocalTime parseTime(String time) throws DukeException {
        int timeInt = Integer.parseInt(time);

        if (time.length() < 4 || timeInt < 0 || timeInt >= 2400) {
            throw new DukeException("Please enter a valid time");
        }

        int hour = Integer.parseInt(time.substring(0,2));;
        int minute = Integer.parseInt(time.substring(2,4));
        return LocalTime.of(hour, minute);
    }
}
