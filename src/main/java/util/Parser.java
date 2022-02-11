package util;

import Commands.*;
import Exceptions.DukeException;
import Exceptions.EmptyDescriptionException;
import Exceptions.InvalidMessageException;

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
     * @param fullCommand The full user input
     * @return A DukeCommand object, indicating the command that is to be executed
     * @throws DukeException
     */
    public static DukeCommand parse(String fullCommand) throws DukeException {
        String commandParts[] = fullCommand.split(" ");
        String command = commandParts[0];
        try {
            if (command.equals("list")) {
                return new ListCommand("list");
            } else if (command.equals("todo")) {
                Parser.exceptionCheck(commandParts.length, command);
                assert commandParts.length == 1 : "Not handling empty body exception correctly";
                return new ToDoCommand(fullCommand.substring(5));
            } else if (command.equals("deadline")) {
                Parser.exceptionCheck(commandParts.length, command);
                assert commandParts.length == 1 : "Not handling empty body exception correctly";
                return new DeadlineCommand(fullCommand.substring(9));
            } else if (command.equals("event")) {
                Parser.exceptionCheck(commandParts.length, command);
                assert commandParts.length == 1 : "Not handling empty body exception correctly";
                return new EventCommand(fullCommand.substring(6));
            } else if (command.equals("mark")) {
                Parser.exceptionCheck(commandParts.length, command);
                assert commandParts.length == 1 : "Not handling empty body exception correctly";
                return new MarkCommand(fullCommand.substring(5));
            } else if (command.equals("unmark")) {
                Parser.exceptionCheck(commandParts.length, command);
                assert commandParts.length == 1 : "Not handling empty body exception correctly";
                return new UnmarkCommand(fullCommand.substring(7));
            } else if (command.equals("delete")) {
                Parser.exceptionCheck(commandParts.length, command);
                assert commandParts.length == 1 : "Not handling empty body exception correctly";
                return new DeleteCommand(fullCommand.substring(7));
            } else if (command.equals("find")) {
                Parser.exceptionCheck(commandParts.length, command);
                assert commandParts.length == 1 : "Not handling empty body exception correctly";
                return new FindCommand(fullCommand.substring(5));
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

    public static String parseDescription(String description) {
       return description.split("/")[0];
    }

    /**
     * Generates and returns a LocalDateTime object from the user inputted description
     * @param description The user inputted string description
     * @param task The task by which a LocalDateTime should be generated for
     * @return LocalDateTime object that
     * @throws DukeException
     */
    public static LocalDateTime parseDateTime(String description, String task) throws DukeException {
        String splitDesc[] = new String[0];

        if (task.equals("deadline")) {
            splitDesc = description.split("/by");
        } else if (task.equals("event")) {
            splitDesc = description.split("/at");
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

        LocalTime localTime;
        LocalDate deadline;

        try {
            deadline = Parser.parseDate(dateTime[1]);
            localTime = Parser.parseTime(dateTime[2]);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter a valid date in the format yyyy-mm-dd");
        } catch (DateTimeException e) {
            throw new DukeException("Please enter a valid time");
        }
        return LocalDateTime.of(deadline, localTime);
    }

    public static LocalDate parseDate(String date) throws DukeException {
        if (date.length() != 10) {
            System.out.println(date.length());
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
