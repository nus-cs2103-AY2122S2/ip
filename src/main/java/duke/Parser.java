package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.commands.*;
import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

public class Parser {

    /**
     * Parses the user input to a task object.
     *
     * @param userInput The string entered by the user.
     * @return The task object parsed from the string.
     * @throws DukeException If a wrong format is entered by the user.
     */
    public static Task parseToTask(String userInput) throws DukeException {

        if (userInput.startsWith("todo")) {
            return Parser.newToDo(userInput);
        } else if (userInput.startsWith("deadline")) {
            return Parser.newDeadline(userInput);
        } else if (userInput.startsWith("event")) {
            return Parser.newEvent(userInput);
        } else {
            // Not used
            return null;
        }
    }
    private static Todo newToDo(String s) throws DukeException {
        String taskName = s.replaceFirst("todo", "").strip();

        if (taskName.equals("")) {
            throw new DukeException("Todo Name is empty!");
        }

        return new Todo(taskName);
    }
    private static Event newEvent(String s) throws DukeException {
        String[] fields = s.replaceFirst("event", "").split("/at");

        if (fields.length != 2) {
            throw new DukeException("Wrong format entered! Please enter <Event Name> /at <Event Date>");
        }

        if (fields[0].strip().equals("")) {
            throw new DukeException("Event Name is empty!");
        }

        if (fields[1].strip().equals("")) {
            throw new DukeException("No Date Specified!");
        }

        String taskName = fields[0].strip();
        String dateString = fields[1].strip();
        LocalDateTime date = Parser.parseDateTime(dateString);

        if (date == null) {
            return new Event(taskName, dateString);
        }

        return new Event(taskName, date);
    }
    private static Deadline newDeadline(String s) throws DukeException {
        String[] fields = s.replaceFirst("deadline", "").split("/by");

        if (fields.length != 2) {
            throw new DukeException("Wrong format entered! Please enter <Deadline Name> /by <Deadline>");
        }

        if (fields[0].strip().equals("")) {
            throw new DukeException("Deadline Name is empty!");
        }

        if (fields[1].strip().equals("")) {
            throw new DukeException("No Date Specified!");
        }

        String taskName = fields[0].strip();
        String dateString = fields[1].strip();
        LocalDateTime date = Parser.parseDateTime(dateString);

        if (date == null) {
            return new Deadline(taskName, dateString);
        }

        return new Deadline(taskName, date);
    }

    /**
     * Converts a Task to the correct format to be saved in the file.
     *
     * @param t The task to be saved.
     * @return The String format of the task to be stored in the file.
     */
    public static String parseToFileFromTask(Task t) {
        String dateStr = "";
        if (t.getDateObj() == null) {
            dateStr = t.getDate();
            if (dateStr.equals("")) {
                dateStr = "None";
            }
        } else {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            dateStr = t.getDateObj().format(format);
        }
        return String.format("%c\t%c\t%s\t%s", t.getType(), t.getDone(), t.getTaskName(), dateStr);
    }

    /**
     * Parses the contents saved in the file to Task objects.
     *
     * @param fileInput The string representing the Task object stored in the file.
     * @return The Task that is parsed.
     * @throws DukeException If the input is in the wrong format.
     */
    public static Task parseToTaskFromFile(String fileInput) throws DukeException {
        // <type>\t<done>\t<name>\t<date>
        char type;
        char done;
        String name;
        String dateStr;
        LocalDateTime date;
        Task t;

        try {
            String[] split = fileInput.split("\t");
            type = split[0].toCharArray()[0];
            done = split[1].toCharArray()[0];
            name = split[2].strip();
            dateStr = split[3];
            date = Parser.parseDateTime(split[3]);
        } catch (IndexOutOfBoundsException exception) {
            throw new DukeException("Unable to load task from file!");
        }

        if (name.equals("")) {
            throw new DukeException("Unable to load task from file!");
        }

        switch(type) {
        case 'T':
            t = new Todo(name);
            break;
        case 'D':
            if (date == null) {
                t = new Deadline(name, dateStr);
            } else {
                t = new Deadline(name, date);
            }
            break;
        case 'E':
            if (date == null) {
                t = new Event(name, dateStr);
            } else {
                t = new Event(name, date);
            }
            break;
        default:
            throw new DukeException("Unable to load task from file!");
        }

        if (done == 'X') {
            t.markDone();
        }

        return t;
    }

    /**
     * Parses the user input into a command.
     *
     * @param input The string given by the user.
     * @return A command representing the command to execute.
     * @throws DukeException If the string entered is not of the correct form.
     */
    public static Command parse(String input) throws DukeException {

        if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
            return new AddCommand(input);
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.equals("list name")) {
            return new SortByNameCommand();
        } else if (input.equals("list date")) {
            return new SortByDateCommand();
        } else if (input.startsWith("mark") || input.startsWith("unmark")) {
            return MarkCommand.of(input);
        } else if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.startsWith("delete")) {
            return new DeleteTaskCommand(input);
        } else if (input.startsWith("find")) {
            return new FindCommand(input);
        } else if (input.startsWith("update")) {
            return UpdateCommand.of(input);
        }

        throw new DukeException("Invalid Command!");
    }

    /**
     * Parses a string into a LocalDateTime object.
     *
     * @param input The string to try to parse into a LocalDateTime object
     * @return The LocalDateTime object if successfully parsed, null if it failed.
     */
    public static LocalDateTime parseDateTime(String input) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            LocalDateTime date = LocalDateTime.parse(input, format);
            return date;
        } catch (DateTimeParseException exception) {
            return null;
        }
    }
}
