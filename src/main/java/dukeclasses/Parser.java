package dukeclasses;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * Represents a Parser that parses user input to make sense of what the user inputs.
 */
public class Parser {

    /**
     * Parses the user input.
     *
     * @param userCommand String that the user inputs.
     * @return ParsedCommand that denotes the command and other relevant information if the
     *         user input is valid.
     * @throws DukeException If user input is invalid.
     */
    public static ParsedCommand parseUserInput(String userCommand, Integer sizeOfTaskList) throws DukeException {
        String[] parsedCommand = userCommand.split(" ", 2);
        parsedCommand[0] = parsedCommand[0].toLowerCase().trim();

        switch (parsedCommand[0]) {
        case "hi":
        case "list":
        case "bye":
            return parseInputWithCommandOnly(parsedCommand[0]);
        case "mark":
        case "unmark":
        case "delete":
        case "recur":
            return parseCommandWithInteger(parsedCommand, sizeOfTaskList);
        case "find":
            return parseFindCommand(parsedCommand);
        case "todo":
            return parseToDoInput(parsedCommand[0], parsedCommand[1]);
        case "event":
            return parseEventInput(parsedCommand[0], parsedCommand[1]);
        case "deadline":
            return parseDeadlineInput(parsedCommand[0], parsedCommand[1]);
        default:
            throw new DukeException();
        }
    }

    /**
     * Parse input where an integer is expected.
     *
     * @param index String that contains the index
     * @param lengthOfTaskList Length of the arrayList supplied.
     * @return ParsedCommand with the right command and input, if not return null.
     */
    private static Integer parseIntegerInCommand(String index, Integer lengthOfTaskList) {
        try {
            Integer taskIndex = Integer.parseInt(index) - 1;
            if (taskIndex >= 0 && taskIndex < lengthOfTaskList) {
                return taskIndex;
            } else {
                return null;
            }
        } catch (NumberFormatException error) {
            return null;
        }
    }

    /**
     * Parses input with a LocalDate expected.
     *
     * @param stringWithDate String that contains the date to be parsed.
     * @return ParsedCommand with the right command and date, else a null is returned.
     */
    public static LocalDate parseDateInput(String stringWithDate, DateTimeFormatter pattern) throws DukeException {
        try {
            return LocalDate.parse(stringWithDate, pattern);
        } catch (DateTimeParseException e) {
            throw new DukeException();
        }
    }

    /**
     * Parses input which contains inputs to construct a ToDo task.
     *
     * @param command String that contains the command the user issued.
     * @param otherData String that contains the task description to construct a ToDo task.
     * @return ParsedCommand that contains the command and task description of the ToDo task.
     * @throws DukeException Exception is thrown when the command supplied is not equal to "todo".
     */
    private static ParsedCommand parseToDoInput(String command, String otherData) throws DukeException {
        if (!command.equals("todo")) {
            throw new DukeException();
        } else {
            return new ParsedCommand(command, otherData.trim());
        }
    }

    /**
     * * Parses input which contains inputs to construct an Event task.
     *
     * @param command String that contains the command the user issued.
     * @param otherData String that contains the task description, date and the recurring period required to construct
     *                  an Event task.
     * @return ParsedCommand that contains the command, task description, date and recurring period used to construct
     *         an Event task.
     * @throws DukeException Exception is thrown when the command supplied is not equal to "event" or when the data is
     *                       insufficient to construct an Event task.
     */
    private static ParsedCommand parseEventInput(String command, String otherData) throws DukeException {
        if (!otherData.contains("/on")) {
            throw new DukeException();
        }
        String[] eventData = otherData.split("/on", 2);
        String task = eventData[0].trim();
        LocalDate date;
        RecurPeriod recurPeriod;
        if (eventData[1].contains("/recur")) {
            String[] dateAndPeriod = eventData[1].split("/recur", 2);
            date = Parser.parseDateInput(dateAndPeriod[0].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            recurPeriod = RecurPeriod.createRecurPeriod(dateAndPeriod[1].trim());
        } else {
            date = Parser.parseDateInput(eventData[1].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            recurPeriod = RecurPeriod.createRecurPeriod("");
        }
        return new ParsedCommand(command, task, date, recurPeriod);
    }

    /**
     * * Parses input which contains inputs to construct a Deadline task.
     *
     * @param command String that contains the command the user issued.
     * @param otherData String that contains the task description, date and the recurring period required to construct
     *                  a Deadline task.
     * @return ParsedCommand that contains the command, task description, date and recurring period used to construct
     *         a Deadline task.
     * @throws DukeException Exception is thrown when the command supplied is not equal to "deadline" or
     *                       when the data is insufficient to construct a Deadline task.
     */
    private static ParsedCommand parseDeadlineInput(String command, String otherData) throws DukeException {
        if (!otherData.contains("/by")) {
            throw new DukeException();
        }
        String[] deadlineData = otherData.split("/by", 2);
        String task = deadlineData[0].trim();
        LocalDate date;
        RecurPeriod recurPeriod;
        if (deadlineData[1].contains("/recur")) {
            String[] dateAndPeriod = deadlineData[1].split("/recur", 2);
            date = Parser.parseDateInput(dateAndPeriod[0].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            recurPeriod = RecurPeriod.createRecurPeriod(dateAndPeriod[1].trim());
        } else {
            date = Parser.parseDateInput(deadlineData[1].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            recurPeriod = RecurPeriod.createRecurPeriod("");
        }
        return new ParsedCommand(command, task, date, recurPeriod);
    }

    /**
     * Parses commands that contain integers.
     *
     * @param commandInputs The command user issued in the form of an array of String.
     * @param sizeOfTaskList The number of elements in the TaskList of Duke.
     * @return ParsedCommand which contains the command and integer of the command issued.
     * @throws DukeException Exception is thrown when the integer larger than the size of TaskList or less than and
     *                       equal to zero. It is also thrown when there is no Integer in the command.
     */
    private static ParsedCommand parseCommandWithInteger(String[] commandInputs,
                                                   Integer sizeOfTaskList) throws DukeException {
        if (commandInputs.length <= 1) {
            throw new DukeException();
        }

        Integer integerInCommand = parseIntegerInCommand(commandInputs[1].trim(), sizeOfTaskList);
        if (integerInCommand == null) {
            throw new DukeException();
        }
        return new ParsedCommand(commandInputs[0].trim(), integerInCommand);
    }

    /**
     * Parses input that correspond to the "find" command.
     * @param splitCommand Array of String used to create a ParsedCommand contains the "find" command logic.
     * @return ParsedCommand that contains the "find" command logic.
     */
    private static ParsedCommand parseFindCommand(String[] splitCommand) {
        return new ParsedCommand(splitCommand[0].trim(), splitCommand[1].trim());
    }

    /**
     * Parese input that correspond to commands that do not need other data to execute.(e.g. list, hi, bye)
     * @param command String that indicates what command it is.
     * @return ParsedCommand that contains the logic of the command.
     */
    private static ParsedCommand parseInputWithCommandOnly(String command) {
        return new ParsedCommand(command.trim());
    }

    /**
     * Parses input that are data used to preload the storage file.
     *
     * @param input String that contains data that correspond to tasks stored in the storage file.
     * @return ParsedData which contains the task information of the data input.
     * @throws DukeException Exception is thrown when the data is in an incorrect format or does not correspond
     *                       to ToDo, Deadline or Event task.
     */
    public static ParsedData parseDataToParsedData(String input) throws DukeException {
        String[] taskMarkings = input.trim().split("]", 3);
        if (taskMarkings.length != 3) {
            throw new DukeException();
        }

        ParsedData parsedData;
        if (taskMarkings[0].contains("T")) {
            parsedData = parseDataForTodo(taskMarkings);
        } else if (taskMarkings[0].contains("E")) {
            parsedData = parseDataForEvent(taskMarkings);
        } else if (taskMarkings[0].contains("D")) {
            parsedData = parseDataForDeadline(taskMarkings);
        } else {
            throw new DukeException();
        }
        return parsedData;
    }

    /**
     * Parses input that contains data corresponding to a ToDo in the storage file.
     *
     * @param data Array of String that contain data used to instantiate a ToDo task.
     * @return ParsedData which contains the data used to instantiate a ToDo task.
     */
    private static ParsedData parseDataForTodo(String[] data) {
        String description = data[2].trim();
        String markingStatus = data[1].trim();
        String command = data[0].trim();
        return new ParsedData("todo", markingStatus, description);
    }

    /**
     * Parses input that contains data corresponding to an Event task in the storage file.
     *
     * @param data Array of String that contain data used to instantiate an Event task.
     * @return ParsedData which contains the data used to instantiate an Event task.
     * @throws DukeException Exception is thrown when the data provided is in the incorrect format or when
     *                       it is insufficient to construct an Event task.
     */
    private static ParsedData parseDataForEvent(String[] data) throws DukeException {
        String markingStatus = data[1].trim();
        String remainingInputForEvent = data[2].trim();

        String[] taskDescriptionAndDueDate = remainingInputForEvent.split("\\(on:", 2);
        String dateString = taskDescriptionAndDueDate[1].replace(")", "").trim();
        String taskDescription = taskDescriptionAndDueDate[0].trim();

        LocalDate date = Parser.parseDateInput(dateString, DateTimeFormatter.ofPattern("MMM dd yyyy"));
        RecurPeriod period = Parser.parseDataToGetRecurPeriod(taskDescription);
        String taskDescriptionWithoutRecurPeriod = Parser.parseDataToDescription(taskDescription);
        return new ParsedData("event", markingStatus, taskDescriptionWithoutRecurPeriod, date, period);
    }

    /**
     * Parses input that contains data corresponding to a Deadline task in the storage file.
     *
     * @param data Array of String that contain data used to instantiate a Deadline task.
     * @return ParsedData which contains the data used to instantiate a Deadline task.
     * @throws DukeException Exception is thrown when the data provided is in the incorrect format or when
     *                       it is insufficient to construct a Deadline task.
     */
    private static ParsedData parseDataForDeadline(String[] data) throws DukeException {
        String markingStatus = data[1].trim();
        String remainingInputForEvent = data[2].trim();

        String[] taskDescriptionAndDueDate = remainingInputForEvent.split("\\(by:", 2);
        String dateString = taskDescriptionAndDueDate[1].replace(")", "").trim();
        String taskDescription = taskDescriptionAndDueDate[0].trim();

        LocalDate date = Parser.parseDateInput(dateString, DateTimeFormatter.ofPattern("MMM dd yyyy"));
        RecurPeriod period = Parser.parseDataToGetRecurPeriod(taskDescription);
        String taskDescriptionWithoutRecurPeriod = Parser.parseDataToDescription(taskDescription);
        return new ParsedData("deadline", markingStatus, taskDescriptionWithoutRecurPeriod, date, period);
    }

    /**
     * Parses input that contains the data that indicate a recurring period.
     *
     * @param str String that contains the recurring period.
     * @return RecurPeriod object that represents the recurring period.
     * @throws DukeException Exception is thrown when the input is insufficient or in an incorrect format to
     *                       instantiate a RecurPeriod object.
     */
    public static RecurPeriod parseDataToGetRecurPeriod(String str) throws DukeException {
        if (str.contains("every")) {
            String[] descriptionAndRecurPeriod = str.split("every");
            return RecurPeriod.createRecurPeriod(descriptionAndRecurPeriod[1].trim());
        } else {
            return RecurPeriod.createRecurPeriod("");
        }
    }

    /**
     * Parses input that contains the task description.
     * @param str String that contains the task description.
     * @return String that only contains the task description.
     */
    public static String parseDataToDescription(String str) {
        if (str.contains("every")) {
            String[] descriptionAndRecurPeriod = str.split("every");
            return descriptionAndRecurPeriod[0].trim();
        } else {
            return str.trim();
        }
    }
}
