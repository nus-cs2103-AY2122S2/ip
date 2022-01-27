package bob;

import bob.exception.BobException;
import bob.exception.InvalidCommandException;
import bob.exception.EventException;
import bob.exception.ToDoException;
import bob.exception.DeadlineException;

import bob.command.DeadlineCommand;
import bob.command.DeleteCommand;
import bob.command.ToDoCommand;
import bob.command.EventCommand;
import bob.command.ByeCommand;
import bob.command.Command;
import bob.command.ListCommand;
import bob.command.MarkCommand;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class Parser {
    public static Command parse(String input) throws BobException {
        String firstWord = input.split(" ")[0];
        if (firstWord.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (firstWord.equalsIgnoreCase("mark") ) {
            try {
                int index = Integer.parseInt(input.substring(4).trim()) - 1;
                return new MarkCommand(index);
            } catch (NumberFormatException e) {
                throw new InvalidCommandException();
            }
        } else if (firstWord.equalsIgnoreCase("delete")) {
            try {
                int index = Integer.parseInt(input.substring(6).trim()) - 1;
                return new DeleteCommand(index);
            } catch (NumberFormatException e) {
                throw new InvalidCommandException();
            }
        } else if (firstWord.equalsIgnoreCase("deadline")) {
            String[] strArr = input.substring(8).split("/by");
            if (strArr.length <= 1) {
                throw new DeadlineException();
            }
            String taskName = strArr[0].trim();
            try {
                LocalDateTime dateTime = LocalDateTime.parse(strArr[1].trim());
                return new DeadlineCommand(taskName, dateTime);
            } catch (DateTimeParseException e) {
                throw new DeadlineException();
            }
        } else if (firstWord.equalsIgnoreCase("todo")) {
            String taskName = input.substring(4).trim();
            String[] strArr = input.substring(4).split(" ");
            if (strArr.length <= 0  || (strArr.length == 1 && strArr[0].isBlank())) {
                throw new ToDoException();
            }
            return new ToDoCommand(taskName);
        } else if (firstWord.equalsIgnoreCase("event")) {
            String[] strArr = input.substring(5).split("/at");
            if (strArr.length <= 1) {
                throw new EventException();
            }
            try {
                String taskName = strArr[0].trim();
                String[] dateTime = strArr[1].trim().split("T");
                LocalDate date = LocalDate.parse(dateTime[0]);
                String[] times = dateTime[1].split("-");
                LocalTime startTime = LocalTime.parse(times[0]);
                LocalTime endTime = LocalTime.parse(times[1]);
                return new EventCommand(taskName, date, startTime, endTime);
            } catch (DateTimeParseException e) {
                throw new EventException();
            }
        } else if (firstWord.equalsIgnoreCase("bye")) {
            return new ByeCommand();
        } else {
            throw new InvalidCommandException();
        }
    }
}
