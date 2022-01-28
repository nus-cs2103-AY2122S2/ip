package util;

import Commands.*;
import Exceptions.DukeException;
import Exceptions.EmptyDescriptionException;
import Exceptions.InvalidMessageException;
import Tasks.Event;
import Tasks.ToDo;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class Parser {

    public static DukeCommand parse(String fullCommand) throws DukeException {
        String commandParts[] = fullCommand.split(" ");
        String command = commandParts[0];

        if (command.equals("list")) {
            return new ListCommand("list");
        } else if (command.equals("todo")) {
            Parser.exceptionCheck(commandParts.length, command);
            return new ToDoCommand(fullCommand.substring(5));
        } else if (command.equals("deadline")) {
            Parser.exceptionCheck(commandParts.length, command);
            return new DeadlineCommand(fullCommand.substring(9));
        } else if (command.equals("event")) {
            Parser.exceptionCheck(commandParts.length, command);
            return new EventCommand(fullCommand.substring(6));
        } else if (command.equals("mark")) {
            Parser.exceptionCheck(commandParts.length, command);
            return new MarkCommand(fullCommand.substring(5));
        } else if (command.equals("unmark")) {
            Parser.exceptionCheck(commandParts.length, command);
            return new UnmarkCommand(fullCommand.substring(7));
        } else if (command.equals("delete")) {
            Parser.exceptionCheck(commandParts.length, command);
            return new DeleteCommand(fullCommand.substring(7));
        } else if (command.equals("find")) {
            Parser.exceptionCheck(commandParts.length, command);
            return new FindCommand(fullCommand.substring(5));
        } else if (command.equals("bye")) {
            return new ByeCommand("bye");
        } else {
            throw new InvalidMessageException();
        }
    }

    public static void exceptionCheck(int size, String command) throws DukeException {
        if (size == 1) {
            throw new EmptyDescriptionException(command);
        }
    }

    public static String parseDescription(String description) {
       return description.split("/")[0];
    }

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

        if (dateTime.length == 1 || dateTime.length == 2) {
            throw new DukeException("Please enter a valid date and time in the format: /by YYYY-MM-DD HHMM");
        }

        String date = dateTime[1];

        if (date.length() != 10) {
            throw new DukeException("Please enter a valid date");
        }


        if (dateTime.length == 2) {
            throw new DukeException("Please enter a time");
        }

        int time = Integer.parseInt(dateTime[2]);

        if (dateTime[2].length() < 4 || time < 0000 || time >= 2400) {
            throw new DukeException("Please enter a valid time");
        }

        int hour = Integer.parseInt(dateTime[2].substring(0,2));;
        int minute = Integer.parseInt(dateTime[2].substring(2,4));

        LocalTime localTime;

        LocalDate deadline = LocalDate.parse("2010-01-01");

        try {
            deadline = LocalDate.parse(date);
            localTime = LocalTime.of(hour, minute);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter a valid date in the format yyyy-mm-dd");
        } catch (DateTimeException e) {
            throw new DukeException("Please enter a valid time");
        }

        return LocalDateTime.of(deadline, localTime);
    }
}
