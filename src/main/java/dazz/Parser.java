package dazz;

import dazz.command.*;
import dazz.exception.DazzException;
import dazz.exception.EmptyDateException;
import dazz.exception.EmptyDescriptionException;
import dazz.exception.IncompleteCommandException;

public class Parser {
    public static Command parse(String input) throws DazzException {
        String[] arr = input.split(" ", 2);
        String[] wordArr;
        switch (arr[0]) {
        case "bye":
            return new ExitCommand();
        case "todo":
            if (arr.length < 2) {
                throw new EmptyDescriptionException();
            } else if (arr[1].trim().equals("")) {
                throw new EmptyDescriptionException();
            }
            return new TodoCommand(arr[1]);
        case "deadline":
            if (arr.length < 2) {
                throw new IncompleteCommandException();
            } else if (arr[1].trim().equals("") || arr[1].trim().startsWith("/by")) {
                throw new EmptyDescriptionException();
            }
            wordArr = arr[1].split(" /by ");
            if (wordArr.length < 2) {
                throw new EmptyDateException();
            }
            return new DeadlineCommand(wordArr[0], Ui.toLocalDateTime(wordArr[1]));
        case "event":
            if (arr.length < 2) {
                throw new IncompleteCommandException();
            } else if (arr[1].trim().equals("") || arr[1].trim().startsWith("/at")) {
                throw new EmptyDescriptionException();
            }
            wordArr = arr[1].split(" /at ");
            if (wordArr.length < 2) {
                throw new EmptyDateException();
            }
            return new EventCommand(wordArr[0], Ui.toLocalDateTime(wordArr[1]));
        case "mark":
            if (arr.length < 2) {
                throw new IncompleteCommandException();
            } else if (arr[1].trim().equals("")) {
                throw new IncompleteCommandException();
            }
            return new MarkCommand(Integer.parseInt(arr[1]));
        case "unmark":
            if (arr.length < 2) {
                throw new IncompleteCommandException();
            } else if (arr[1].trim().equals("")) {
                throw new IncompleteCommandException();
            }
            return new UnmarkCommand(Integer.parseInt(arr[1]));
        case "delete":
            if (arr.length < 2) {
                throw new IncompleteCommandException();
            } else if (arr[1].trim().equals("")) {
                throw new IncompleteCommandException();
            }
            return new DeleteCommand(Integer.parseInt(arr[1]));
        case "list":
            return new ListCommand();
        default:
            return new DefaultCommand();
        }
    }
}