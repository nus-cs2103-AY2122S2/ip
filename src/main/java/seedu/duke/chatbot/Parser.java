package seedu.duke.chatbot;

import seedu.duke.command.AddCommand;
import seedu.duke.command.Command;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.FindCommand;
import seedu.duke.command.ListCommand;
import seedu.duke.command.MarkCommand;
import seedu.duke.command.UnmarkCommand;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.IncompleteCommandException;
import seedu.duke.exceptions.NoCommandException;
import seedu.duke.exceptions.NoDateException;
import seedu.duke.exceptions.NoValidTaskIndexException;
import seedu.duke.task.Deadline;
import seedu.duke.task.Event;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.task.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;

/**
 * Handles commands from users in String and filters the information needed to create {@link Command}.
 */
public class Parser {

    /**
     * Creates a Parser, which processes command inputs from users, given in String.
     */
    public Parser() {}

    /**
     *Parses string commands from user into information needed to execute the command.
     * @param command which is a string input by the user with a command keyword.
     * @return {@link Command } which starts the execution of the command
     * @throws DukeException if an unknown command keyword or incomplete command is given
     */
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
            return new ExitCommand();
        } else if (command.startsWith("find")) {
            String searchString = this.parseForSearch(command);
            return new FindCommand(searchString);
        } else {
            throw new NoCommandException();
        }
    }

    /**
     * Parses a command that seeks to mark a task.
     * @param command is a string with a command keyword "mark" from user
     * @return the index of the {@link Task} to mark in {@link seedu.duke.task.TaskList}
     * @throws DukeException if an invalid task number is given from user
     */
    int parseForMark(String command) throws DukeException {
        int indexAfterCommand = 5;
        //command is given as "mark 5" so index 5 is where "5" is at

        if (command.length() <= 5) { //e.g. mark vs "mark 1" (correct)
            throw new NoValidTaskIndexException();
        }

        //-1 because index in taskList is 0 based but command uses 1-based index
        int indexOfTaskToMark = Integer
                .parseInt(command
                        .substring(indexAfterCommand, indexAfterCommand + 1)) - 1;

        return indexOfTaskToMark;
    }

    /**
     * Parses a command that seeks to unmark a task.
     * @param command is a string with a command keyword "unmark" from user
     * @return the index of the {@link Task} to unmark in {@link seedu.duke.task.TaskList}
     * @throws DukeException if an invalid task number is given from user
     */
    int parseForUnmark(String command) throws DukeException {
        int indexAfterCommand = 7;
        //command is given as "unmark 5" so index 7 is where the task index to parse, "5" is at

        if (command.length() <= 7) { //e.g. "unmark " vs "unmark 1" (correct)
            throw new NoValidTaskIndexException();
        }

        int indexToUnmark = Integer
                .parseInt(command
                        .substring(indexAfterCommand)) - 1;

        return indexToUnmark;
    }

    /**
     * Parses a command that seeks to delete a task.
     * @param command is a string with a command keyword "delete" from user
     * @return the index of the {@link Task} to delete in {@link seedu.duke.task.TaskList}
     * @throws DukeException if an invalid task number is given from user
     */
    int parseForDelete(String command) throws DukeException {
        int indexTaskName = 7;
        //command is given as "delete <taskIndex>" so taskIndex is at index 7

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

    /**
     * Parses a command that seeks to add a ToDo o to {@link seedu.duke.task.TaskList}.
     * @param command is a string with a command keyword "todo"
     * @return {@link ToDo} to add to {@link seedu.duke.task.TaskList}
     * @throws DukeException if an incomplete command is given from user
     */
    Task parseForTodo(String command) throws DukeException {
        int indexTaskName = 5;
        //command is given as "todo <taskName>" so task name starts at index 5

        if (command.length() <= 5) {
            throw new IncompleteCommandException();
        }

        String taskName = command.substring(indexTaskName);

        return new ToDo(taskName);
    }

    /**
     * Creates a {@link java.time.LocalDateTime} from a String command detailing the date.
     * @param dateStr is a string detailing the date in yyyy-MM-dd HH:mm format
     * @return {@link LocalDateTime} object to create {@link Deadline} and {@link Event}
     * @throws DukeException if wrongly formatted date or no date is given from user.
     */
    LocalDateTime getLocalDateTimeFromDate(String dateStr) throws DukeException {
        try {
            TemporalAccessor ta = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").parse(dateStr);
            LocalDateTime date = LocalDateTime.from(ta);
            return date;
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Parses a command that seeks to add a {@link Deadline} to {@link seedu.duke.task.TaskList}.
     * @param command is a string with a command keyword "deadline" from user
     * @return {@link Deadline} to add to {@link seedu.duke.task.TaskList}
     * @throws DukeException if an incomplete command is given from user
     */
    Task parseForDeadline(String command) throws DukeException {
        int indexTaskName = 9;
        //command is given as "deadline <taskname> /by <date>" so index 9 is where task name starts

        if (command.length() <= 9) { //e.g. "deadline " vs "deadline return book /by Sunday" (correct)
            throw new IncompleteCommandException();
        }

        //command is given as "deadline <taskname> /at <date>" so find "/by"
        // to find where to cut the string for <date>
        int dateMarkerIndex = command.indexOf("/by");
        if (dateMarkerIndex == -1) { // "/" does not exist
            throw new NoDateException();
        }

        String taskName = command
                .substring(indexTaskName, dateMarkerIndex);
        //command is given as "deadline <taskname> /by <date>" so <date> can be found
        // +4 away from index of "/"
        int indexStartOfDate = dateMarkerIndex + 4;  //"/by yyyy-mm-dd hh:mm"
        if (indexStartOfDate >= command.length()) {
            //e.g."deadline /" is invalid ; "deadline /by " is also invalid
            // because there is no date after "/by"
            throw new NoDateException();
        }

        String dateString = command.substring(indexStartOfDate);
        LocalDateTime date = this.getLocalDateTimeFromDate(dateString);

        return new Deadline(taskName, date);
    }

    /**
     * Parses a command seeks to to add a {@link Event} to {@link seedu.duke.task.TaskList}.
     * @param command is a string with a command keyword "event"
     * @return {@link Event} to add to {@link seedu.duke.task.TaskList}
     * @throws DukeException if an incomplete command is given from user
     */
    Task parseForEvent(String command) throws DukeException {
        int indexTaskName = 6;
        //command is given as "event <taskname> /at <date>" so index 7 is where the task index to parse, "5" is at

        if (command.length() <= 6) { //e.g. "event " vs "event project meeting /at Mon 2-4pm"
            throw new IncompleteCommandException();
        }

        //command is given as "event <taskname> /at <date>" so find "/at"
        // to find where to cut the string for <date>
        int dateMarkerIndex = command.indexOf("/at");
        if (dateMarkerIndex == -1) {
            throw new NoDateException();
        }
        //+2 is because of "at " that occurs before the date then +1 for a space "at "
        String taskName = command
                .substring(indexTaskName, dateMarkerIndex);

        //command is given as "event <taskname> /at <date>" so <date> can be found
        // +4 away from index of "/"
        int indexStartOfDate = dateMarkerIndex + 4;  //"/at <date>"
        if (indexStartOfDate >= command.length()) { //e.g."deadline /" is invalid ; "deadline /by "
            throw new NoDateException();
        }

        String dateString = command.substring(indexStartOfDate);
        LocalDateTime date = this.getLocalDateTimeFromDate(dateString);

        return new Event(taskName, date);
    }

    /**
     * Parses a command that seeks to search a {@link Task} in {@link TaskList}.
     * @param command is a string like "search book" to find tasks with the word book in it
     * @return String Keyword to search in {@link seedu.duke.task.TaskList}
     */
    String parseForSearch(String command) {
        int indexToStart = 5; //command is given as "find <keyword>", the keyword starts at index 5
        return command.substring(5);
    }
}