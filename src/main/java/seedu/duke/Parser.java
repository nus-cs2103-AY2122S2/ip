package seedu.duke;

import seedu.duke.command.*;
import seedu.duke.exceptions.*;
import seedu.duke.task.Deadline;
import seedu.duke.task.Event;
import seedu.duke.task.Task;
import seedu.duke.task.ToDo;

public class Parser {
    private final String userName;

    Parser(String userName) {
        this.userName = userName;
    }

    Command parse(String command) throws DukeException {
        if (command.startsWith("list")) {
            return new ListCommand();
        } else if (command.startsWith("mark")) {
            int indexToMark = this.parseForMark(command);
            return new MarkCommand(indexToMark);
        } else if (command.startsWith("unmark")) {
            int indexToUnmark = this.parseForUnmark(command);
            return new UnmarkCommand(indexToUnmark);
        } else if (command.startsWith("todo")) {
            Task newTask = this.parseForTodo(command);
            return new AddCommand(newTask);
        } else if (command.startsWith("deadline")) {
            Task newTask = this.parseForDeadline(command);
            return new AddCommand(newTask);
        } else if (command.startsWith("event")) {
            Task newTask = this.parseForEvent(command);
            return new AddCommand(newTask);
        } else if (command.startsWith("delete")) {
            //command is given as "delete <taskIndex>"
            int index = this.parseForDelete(command);
            return new DeleteCommand(index);
        } else if (command.startsWith("bye")) {
            return new ExitCommand(this.userName);
        } else {
            throw new NoCommandException();
        }
    }

    int parseForMark(String command) throws DukeException {
        int indexAfterCommand = 5;
        if (command.length() <= 5) { //e.g. mark vs "mark 1" (correct)
            throw new NoValidTaskIndexException();
        }
        //-1 because index in taskList is 0 based but command uses 1-based index
        int indexOfTaskToMark = Integer
                .parseInt(command
                        .substring(indexAfterCommand, indexAfterCommand + 1)) - 1;
        return indexOfTaskToMark;
    }

    int parseForUnmark(String command) throws DukeException {
        int indexAfterCommand = 7;
        if (command.length() <= 7) { //e.g. "unmark " vs "unmark 1" (correct)
            throw new NoValidTaskIndexException();
        }
        int indexToUnmark = Integer
                .parseInt(command
                        .substring(indexAfterCommand)) - 1;
        return indexToUnmark;
    }

    int parseForDelete(String command) throws DukeException {
        int indexTaskName = 7;
        //command is given as "delete <taskIndex>"
        if (command.length() <= 7) { //e.g. "delete " vs "delete 1" (correct)
            throw new NoValidTaskIndexException();
        }
        //+1 in substring is in case of extra space at the end
        //-1 is because 1 based index is used in command
        int indexToUnmark = Integer
                .parseInt(command
                        .substring(indexTaskName, indexTaskName + 1)) - 1;
        return indexToUnmark;
    }

    Task parseForTodo(String command) throws DukeException {
        int indexTaskName = 5;
        if (command.length() <= 5) {
            throw new IncompleteCommandException();
        }
        String taskName = command.substring(indexTaskName);
        return new ToDo(taskName);
    }

    Task parseForDeadline(String command) throws DukeException {
        int indexTaskName = 9;
        if (command.length() <= 9) { //e.g. "deadline " vs "deadline return book /by Sunday" (correct)
            throw new IncompleteCommandException();
        }
        int dateMarkerIndex = command.indexOf("/by");
        if (dateMarkerIndex == -1) { // "/" does not exist
            throw new NoDateException();
        }
        String taskName = command
                .substring(indexTaskName, dateMarkerIndex);
        int indexStartOfDate = dateMarkerIndex + 4;  //"/by <date>"
        if (indexStartOfDate >= command.length()) { //e.g."deadline /" is invalid ; "deadline /by "
            throw new NoDateException();
        }
        String date = command.substring(indexStartOfDate);
        return new Deadline(taskName, date);
    }

    Task parseForEvent(String command) throws DukeException {
        int indexTaskName = 6;
        if (command.length() <= 6) { //e.g. "event " vs "event project meeting /at Mon 2-4pm"
            throw new IncompleteCommandException();
        }
        int dateMarkerIndex = command.indexOf("/at");
        if (dateMarkerIndex == -1) {
            throw new NoDateException();
        }
        //+2 is because of "at " that occurs before the date then +1 for a space "at "
        String taskName = command
                .substring(indexTaskName, dateMarkerIndex);
        int indexStartOfDate = dateMarkerIndex + 4;  //"/at <date>"
        if (indexStartOfDate >= command.length()) { //e.g."deadline /" is invalid ; "deadline /by "
            throw new NoDateException();
        }
        String date = command.substring(indexStartOfDate);
        return new Event(taskName, date);
    }
}