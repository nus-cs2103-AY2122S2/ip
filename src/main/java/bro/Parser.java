package bro;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import bro.commands.*;
import bro.exceptions.BroException;
import bro.tasks.Deadline;
import bro.tasks.Event;
import bro.tasks.Task;
import bro.tasks.Todo;

public class Parser {

    /**
     * Parses the user input to a task object.
     *
     * @param userInput The string entered by the user.
     * @return The task object parsed from the string.
     * @throws BroException If a wrong format is entered by the user.
     */
    public static Task parseToTask(String userInput) throws BroException {

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
    private static Todo newToDo(String s) throws BroException {
        String taskName = s.replaceFirst("todo", "").strip();

        if (taskName.equals("")) {
            throw new BroException("Todo Name is empty!");
        }

        return new Todo(taskName);
    }
    private static Event newEvent(String s) throws BroException {
        String[] fields = s.replaceFirst("event", "").split("/at");

        if (fields.length != 2) {
            throw new BroException("Wrong format entered! Please enter <Event Name> /at <Event Date>");
        }

        if (fields[0].strip().equals("")) {
            throw new BroException("Event Name is empty!");
        }

        if (fields[1].strip().equals("")) {
            throw new BroException("No Date Specified!");
        }

        String taskName = fields[0].strip();
        String dateString = fields[1].strip();
        assert !dateString.equals("") : "No date supplied!";
        LocalDateTime date = Parser.parseDateTime(dateString);

        if (date == null) {
            return new Event(taskName, dateString);
        }

        return new Event(taskName, date);
    }
    private static Deadline newDeadline(String s) throws BroException {
        String[] fields = s.replaceFirst("deadline", "").split("/by");

        if (fields.length != 2) {
            throw new BroException("Wrong format entered! Please enter <Deadline Name> /by <Deadline>");
        }

        if (fields[0].strip().equals("")) {
            throw new BroException("Deadline Name is empty!");
        }

        if (fields[1].strip().equals("")) {
            throw new BroException("No Date Specified!");
        }

        String taskName = fields[0].strip();
        String dateString = fields[1].strip();
        assert !dateString.equals("") : "No date supplied!";
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
     * @throws BroException If the input is in the wrong format.
     */
    public static Task parseToTaskFromFile(String fileInput) throws BroException {
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
            if (name.equals("")) {
                throw new BroException("Unable to load task from file!");
            }
            dateStr = split[3];
            date = Parser.parseDateTime(split[3]);
        } catch (IndexOutOfBoundsException exception) {
            throw new BroException("Unable to load task from file!");
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
            throw new BroException("Unable to load task from file!");
        }

        if (done == 'X') {
            t.markDone();
        }

        assert t != null : "No task parsed!";

        return t;
    }

    /**
     * Parses the user input into a command.
     *
     * @param input The string given by the user.
     * @return A command representing the command to execute.
     * @throws BroException If the string entered is not of the correct form.
     */
    public static Command parse(String input) throws BroException {

        input = input.strip();

        if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
            return newAddCommand(input);
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.equals("list name")) {
            return new SortByNameCommand();
        } else if (input.equals("list date")) {
            return new SortByDateCommand();
        } else if (input.equals("help")) {
            return new HelpCommand();
        } else if (input.startsWith("mark")) {
            return newMarkDoneCommand(input.replaceFirst("mark", ""));
        } else if (input.startsWith("unmark")) {
            return newMarkUndoneCommand(input.replaceFirst("unmark", ""));
        } else if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.startsWith("delete")) {
            return newDeleteTaskCommand(input.replaceFirst("delete", ""));
        } else if (input.startsWith("find")) {
            return newFindCommand(input.replaceFirst("find", ""));
        } else if (input.startsWith("update")) {
            return newUpdateCommand(input.replaceFirst("update", ""));
        }

        throw new BroException("Invalid Command!");
    }

    /**
     * Parses the user input into an add command.
     *
     * @param input The user input specifying the task to add.
     * @return The command to add the task specified by the user.
     * @throws BroException If the user input fails to parse to a Task.
     */
    public static AddCommand newAddCommand(String input) throws BroException {
        Task taskToAdd = Parser.parseToTask(input);
        assert taskToAdd != null : "Failed to parse user input to task!";
        return new AddCommand(taskToAdd);
    }

    /**
     * Parses the user input into a delete command.
     *
     * @param index The user input to parse.
     * @return The command to delete the task specified with an index.
     * @throws BroException If an invalid index is entered.
     */
    public static DeleteTaskCommand newDeleteTaskCommand(String index) throws BroException {
        try {
            int indexToDelete = Integer.parseInt(index.strip()) - 1;
            return new DeleteTaskCommand(indexToDelete);
        } catch (NumberFormatException e) {
            throw new BroException("Invalid number entered! Please enter an integer");
        }
    }

    /**
     * Parses the user input into a find command.
     *
     * @param keyword The keyword to search the tasks for.
     * @return The command to find tasks containing the keyword.
     */
    public static FindCommand newFindCommand(String keyword) {
        keyword = keyword.strip();
        assert !keyword.equals("") : "No keyword supplied!";
        return new FindCommand(keyword);
    }

    /**
     * Parses the user input into a mark done command.
     *
     * @param input The user input to parse.
     * @return The command to mark the task as done.
     * @throws BroException If an invalid index is entered.
     */
    public static MarkDoneCommand newMarkDoneCommand(String input) throws BroException {
        try {
            int indexToMark = Integer.parseInt(input.strip()) - 1;
            return new MarkDoneCommand(indexToMark);
        } catch (NumberFormatException e) {
            throw new BroException("Invalid number entered! Please enter an integer");
        }
    }

    /**
     * Parses the user input into a mark undone command.
     *
     * @param input The user input to parse.
     * @return The command to mark the task as undone.
     * @throws BroException If an invalid index is entered.
     */
    public static MarkUndoneCommand newMarkUndoneCommand(String input) throws BroException {
        try {
            int indexToMark = Integer.parseInt(input.strip()) - 1;
            return new MarkUndoneCommand(indexToMark);
        } catch (NumberFormatException e) {
            throw new BroException("Invalid number entered! Please enter an integer");
        }
    }

    /**
     * Parses the user input into an update command.
     *
     * @param input The user input to parse.
     * @return The command to update the name or date of the task.
     * @throws BroException If an invalid index is entered.
     */
    public static UpdateCommand newUpdateCommand(String input) throws BroException {

        try {
            int indexToUpdate = Integer.parseInt(input.strip().split(" ")[0]) - 1;

            if (input.contains("/name")) {
                String newName = input.split("/name")[1];
                return new UpdateNameCommand(indexToUpdate, newName);
            }

            if (input.contains("/date")) {
                String inputDate = input.split("/date")[1];
                assert !inputDate.equals("") : "No date supplied!";
                LocalDateTime date = Parser.parseDateTime(inputDate);

                if (date != null) {
                    return new UpdateDateCommand(indexToUpdate, date);
                } else {
                    return new UpdateDateCommand(indexToUpdate, inputDate);
                }
            }

            throw new BroException("Invalid format entered! "
                    + "Please enter \"/date\" to change date or \"/name\" to change name");

        } catch (NumberFormatException e) {
            throw new BroException("Invalid number entered! Please enter an integer");
        } catch (IndexOutOfBoundsException e) {
            throw new BroException("No name or date entered!");
        }
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
            LocalDateTime date = LocalDateTime.parse(input.strip(), format);
            return date;
        } catch (DateTimeParseException exception) {
            return null;
        }
    }
}
