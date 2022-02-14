package seedu.duke.chatbot;

import seedu.duke.command.*;
import seedu.duke.exceptions.*;
import seedu.duke.task.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;

//Duke can only handle 10 to dos

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
        } else if (command.startsWith("delete") && command.length() == 8) {
            //command is given as "delete <taskIndex>"
            int index = this.parseForDelete(command);
            return new DeleteCommand(index);
        } else if (command.startsWith("bye")) {
            return new ExitCommand();
        } else if (command.startsWith("find")) {
            String searchString = this.parseForSearch(command);
            return new FindCommand(searchString);
        } else if (command.startsWith("add note")) {
            String noteContent = this.parseForAddNoteContent(command);
            int indexTaskToEdit = this.parseForAddNoteTaskIndex(command);
            return new AddNoteCommand(noteContent, indexTaskToEdit);
        } else if (command.startsWith("show note")) {
            int indexTaskToShow = this.parseForShowNoteTaskIndex(command);
            return new ShowNoteCommand(indexTaskToShow);
        } else if (command.startsWith("clear note")) {
            int indexTaskToDelete = this.parseForDeleteNoteTaskIndex(command);
            int indexNoteToDelete = this.parseForDeleteNoteIndex(command);
            return new DeleteNoteCommand(indexTaskToDelete,indexNoteToDelete);
        } else if (command.startsWith("edit note")) {
            int indexTaskToEdit = this.parseForEditNoteTaskIndex(command);
            int indexNoteToEdit = this.parseForEditNoteIndex(command);
            String noteContent = this.parseForEditNoteContent(command);
            return new EditNoteCommand(indexTaskToEdit,indexNoteToEdit,noteContent);
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
        int indexInCommand = 5;
        //command is given as "mark 5" so index 5 is where "5" is at

        if (command.length() <= 5) { //e.g. mark vs "mark 1" (correct)
            throw new NoValidTaskIndexException();
        }

        //-1 because index in taskList is 0 based but command uses 1-based index
        int indexOfTaskToMark =  this.parseForTaskIndex(command, indexInCommand);

        return indexOfTaskToMark;
    }

    /**
     * Parses a command that seeks to unmark a task.
     * @param command is a string with a command keyword "unmark" from user
     * @return the index of the {@link Task} to unmark in {@link seedu.duke.task.TaskList}
     * @throws DukeException if an invalid task number is given from user
     */
    int parseForUnmark(String command) throws DukeException {
        int indexInCommand = 7;
        //command is given as "unmark 5" so index 7 is where the task index to parse, "5" is at

        if (command.length() <= 7) { //e.g. "unmark " vs "unmark 1" (correct)
            throw new NoValidTaskIndexException();
        }

        int indexToUnmark = this.parseForTaskIndex(command, indexInCommand);

        return indexToUnmark;
    }

    /**
     * Parses a command that seeks to delete a task.
     * @param command is a string with a command keyword "delete" from user
     * @return the index of the {@link Task} to delete in {@link seedu.duke.task.TaskList}
     * @throws DukeException if an invalid task number is given from user
     */
    int parseForDelete(String command) throws DukeException {
        try {
            int indexInCommand = 7;
            //command is given as "delete <task number>" so task number is at index 7

            if (command.length() <= 7) { //e.g. "delete " vs "delete 1" (correct)
                throw new NoValidTaskIndexException();
            }
            //+1 in substring is in case of extra space at the end
            //-1 is because 1 based index is used in command

            int indexToUnmark = this.parseForTaskIndex(command, indexInCommand);

            return indexToUnmark;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Problem with parseForDelete");
        }
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
        //command is given as "event <taskname> /at <startDate> /to <endDate>" so index 7 is where the task index to parse, "5" is at

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

        //command is given as "event <taskname> /at <startDate> /to <endDate>" so <date> can be found
        // +4 away from index of "/"
        int indexStartDate = dateMarkerIndex + 4;  //"/at <date>"
        if (indexStartDate >= command.length()) { //e.g."deadline /" is invalid ; "deadline /by "
            throw new NoDateException();
        }

        int indexOfTo = command.indexOf("/to");

        String startDateString = command.substring(indexStartDate, indexOfTo - 1); // /at <date> /to
        LocalDateTime startDate = this.getLocalDateTimeFromDate(startDateString);

        int indexEndDate = indexOfTo + 4; ///to <endDate>
        String endDateString = command.substring(indexEndDate);
        LocalDateTime endDate = this.getLocalDateTimeFromDate(endDateString);

        return new Event(taskName, endDate, startDate);
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

    String parseForAddNoteContent(String command) throws DukeException {
        //command is given as "add note to task <task number> <note content>"
        //e.g. add note to 1 remind groupmates"
        int indexToStart = 19;
        return this.parseForContent(command, indexToStart);
    }

    int parseForAddNoteTaskIndex(String command) throws DukeException {
        //command is given as "add note to <task number> <note content>"
        //e.g. add note 1 remind groupmates"
        try {
            int indexInCommand = 17;
            return this.parseForTaskIndex(command, indexInCommand);
        } catch (NumberFormatException |  IndexOutOfBoundsException e) {
            throw new DukeException("Problem with getting index to add note to");
        }
    }

    int parseForShowNoteTaskIndex(String command) throws DukeException {
        //command is given as "show note from task <task number>"
        //e.g. show note 1
        try {
            int indexInCommand = 20;
            return this.parseForTaskIndex(command, indexInCommand);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new IncompleteCommandException();
        }
    }

    int parseForDeleteNoteTaskIndex(String command) throws DukeException {
        //command is given as "clear note <notenumber> from <tasknumber>"
        try {
            int indexInCommand = 23;
            return this.parseForTaskIndex(command, indexInCommand);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("Can't seem to get the task index to delete note");
        }
    }

    int parseForDeleteNoteIndex(String command) throws DukeException {
        //command is given as "clear note <notenumber> from task <taskNumber>"
        try {
            int indexInCommand = 11;
            return this.parseForTaskIndex(command, indexInCommand);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("Can't seem to get the note index to delete note");
        }
    }

    int parseForEditNoteTaskIndex(String command) throws DukeException {
        //command is given as "edit note <notenumber> from task <tasknumber> <noteContent>"
        try {
            int indexInCommand = 22;
            return this.parseForTaskIndex(command, indexInCommand);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("Can't seem to get the task index to edit note");
        }
    }

    int parseForEditNoteIndex(String command) throws DukeException {
        //command is given as "edit note <notenumber> from task <tasknumber> <noteContent>"
        try {
            int indexInCommand = 10;
            return this.parseForTaskIndex(command, indexInCommand);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("Can't seem to get the note index to edit note");
        }
    }

    String parseForEditNoteContent(String command) throws DukeException {
        //command is given as "edit note <notenumber> from task <tasknumber> <noteContent>"
        int indexToStart = 24;
        return this.parseForContent(command, indexToStart);
    }

    /**
     * To return an integer containing the task index to execute on from a command.
     * @param command which is a string containing information on what to execute
     * @param indexInCommand which contains the location in command to parse for
     * @return index of concern
     */
    int parseForTaskIndex(String command, int indexInCommand) {
        return Integer
                .parseInt(command.substring(indexInCommand, indexInCommand + 1)) - 1;
    }

    String parseForContent(String command, int indexContent) throws DukeException {
        try {
            return command.substring(indexContent);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Problem with getting content");
        }
    }
}