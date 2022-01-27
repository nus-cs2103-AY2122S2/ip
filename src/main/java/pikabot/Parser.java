package pikabot;

import pikabot.command.Command;
import pikabot.command.DeadlineCommand;
import pikabot.command.DeleteCommand;
import pikabot.command.EventCommand;
import pikabot.command.InvalidCommand;
import pikabot.command.ListCommand;
import pikabot.command.MarkCommand;
import pikabot.command.TodoCommand;
import pikabot.command.UnmarkCommand;

import pikabot.task.Deadline;
import pikabot.task.Event;
import pikabot.task.Todo;

import pikabot.exception.DeadlineException;
import pikabot.exception.EventException;
import pikabot.exception.TodoException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Parser {

    //parse command
    public static Command parseCommand(String[] inputArr) {
        switch(inputArr[0].toLowerCase(Locale.ROOT)) {
        case "deadline": {
            return new DeadlineCommand(inputArr);
        }
        case "delete": {
            return new DeleteCommand(inputArr);
        }
        case "event": {
            return new EventCommand(inputArr);
        }
        case "list": {
            return new ListCommand(inputArr);
        }
        case "mark": {
            return new MarkCommand(inputArr);
        }
        case "todo": {
            return new TodoCommand(inputArr);
        }
        case "unmark": {
            return new UnmarkCommand(inputArr);
        }
        default: {
            return new InvalidCommand(inputArr);
        }
        }
    }


    //parseTodo
    public static Todo parseTodo(String[] todoArray) throws TodoException {
        if (todoArray.length == 1) {
            throw new TodoException();
        } else {
            String description = todoArray[1];
            return new Todo(description);
        }
    }

    //parseDeadline
    public static Deadline parseDeadline(String[] deadlineArray) throws DeadlineException {
        if (deadlineArray.length == 1) {
            throw new DeadlineException("The description of a deadline cannot be empty.");
        } else {
            String[] deadlineDetails = deadlineArray[1].split("/by ", 2);
            if (deadlineDetails.length == 1) {
                throw new DeadlineException("The description of a deadline must contain " +
                    "a deadline in the numerical format YYYY-MM-DD");
            } else {
                try {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    return new Deadline(deadlineDetails[0], LocalDate.parse(deadlineDetails[1], dtf));
                } catch (DateTimeParseException e) {
                    throw new DeadlineException("Invalid deadline! Deadline has to be a " +
                        "valid date in numerical format YYYY-MM-DD");
                }
            }
        }
    }


    //parseEvent
    public static Event parseEvent(String[] eventArray) throws EventException {
        if (eventArray.length == 1) {
            throw new EventException("The description of an event cannot be empty.");
        } else {
            String[] eventDetails = eventArray[1].split("/at ", 2);
            if (eventDetails.length == 1) {
                throw new EventException("The description of an event must contain a date in " +
                    "the numerical format YYYY-MM-DD");
            } else {
                try {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    return new Event(eventDetails[0], LocalDate.parse(eventDetails[1], dtf));
                } catch (DateTimeParseException e) {
                    throw new EventException("Invalid exception! Event has to have a valid date in " +
                        "numerical format YYYY-MM-DD");
                }
            }
        }
    }
}
