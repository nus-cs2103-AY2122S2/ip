package duke.parser;

import duke.command.*;
import duke.exception.DukeCommandException;
import duke.exception.DukeException;
import duke.exception.DukeInvalidArgumentException;
import duke.exception.DukeMissingArgumentException;
import duke.task.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Parser {
    public static final DateTimeFormatter formatter = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern("yyyy-MM-dd HHmm")
            .toFormatter(Locale.ENGLISH);

    public static Command parse(String inputCommand) throws DukeException {
        if (inputCommand.length() == 0) {
            try {
                throw new DukeCommandException("");
            } catch (DukeCommandException e) {
                System.out.println(e);
                return new InvalidCommand();
            }
        }
        String[] inputArray = inputCommand.split(" ");
        String firstArg = inputArray[0];
        if (inputArray.length == 1) {
            if (Command.CommandType.BYE.equals(firstArg)) {
                return new ByeCommand(inputArray);
            } else if (Command.CommandType.LIST.equals(firstArg)) {
                return new ListCommand(inputArray);
            } else {
                throw new DukeMissingArgumentException(firstArg);
            }
        } else {
            String secondArg = inputArray[1];
            if (Command.CommandType.DELETE.equals(firstArg)) {
                try {
                    int index = Integer.parseInt(secondArg);
                    return new DeleteCommand(index, inputArray);
                } catch (NumberFormatException e) {
                    System.out.println("    index must be a number!");
                }
            } else if (Command.CommandType.DEADLINE.equals(firstArg)) {
                if (secondArg.equals("\\by")) {
                    throw new DukeMissingArgumentException("task description");
                }
                String content = "";
                int indexOfBy = inputCommand.lastIndexOf("\\by ");
                if (indexOfBy == -1) {
                    throw new DukeMissingArgumentException("\\by deadlineTime");
                } else {
                    String by = inputCommand.substring(indexOfBy + 4);
                    content = inputCommand.substring(9, indexOfBy - 1);
                    LocalDateTime date = null;
                    try {
                        date = LocalDateTime.parse(by, formatter);
                        Task taskObj = new Deadline(content, date);
                        return new AddCommand(taskObj, inputArray);
                    } catch (DateTimeParseException e) {
                        System.out.println("Wrong date format!");
                        e.printStackTrace();
                    }
                }
            } else if (Command.CommandType.EVENT.equals(firstArg)) {
                if (secondArg.equals("\\by")) {
                    throw new DukeMissingArgumentException("task description");
                }
                String content = "";
                int indexOfAt = inputCommand.lastIndexOf("\\at ");
                if (indexOfAt == -1) {
                    throw new DukeMissingArgumentException("\\at eventTime");
                } else {
                    String by = inputCommand.substring(indexOfAt + 4);
                    content = inputCommand.substring(6, indexOfAt - 1);
                    LocalDateTime date = null;
                    try {
                        date = LocalDateTime.parse(by, formatter);
                        Task taskObj = new Event(content, date);
                        return new AddCommand(taskObj, inputArray);
                    } catch (DateTimeParseException e) {
                        System.out.println("Wrong date format!");
                        e.printStackTrace();
                    }
                }
            } else if (Command.CommandType.TODO.equals(firstArg)) {
                String content = "";
                for (int k = 1; k < inputArray.length; k++) {
                    content += inputArray[k];
                }
                Task taskObj = new ToDo(content);
                return new AddCommand(taskObj, inputArray);
            } else if (Command.CommandType.MARK.equals(firstArg)) {
                try {
                    int index = Integer.parseInt(secondArg);
                    return new MarkCommand(index, MarkCommand.Mark.MARK, inputArray);
                } catch (NumberFormatException e) {
                    System.out.println("    index must be a number!");
                }
            } else if (Command.CommandType.UNMARK.equals(firstArg)) {
                try {
                    int index = Integer.parseInt(secondArg);
                    return new MarkCommand(index, MarkCommand.Mark.UNMARK, inputArray);
                } catch (NumberFormatException e) {
                    System.out.println("    index must be a number!");
                }
            } else if (Command.CommandType.SORT.equals(firstArg)) {
                if (TaskList.SortType.CONTENT.equals(secondArg)) {
                    return new SortCommand(TaskList.SortType.CONTENT, inputArray);
                } else if (TaskList.SortType.DATE.equals(secondArg)) {
                    return new SortCommand(TaskList.SortType.DATE, inputArray);
                } else {
                    throw new DukeInvalidArgumentException("sort type");
                }
            } else {
                return new InvalidCommand();
            }
        }
        return new InvalidCommand();
    }
}
