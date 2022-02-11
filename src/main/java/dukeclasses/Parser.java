package dukeclasses;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

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
    public static ParsedCommand parse(String userCommand, Integer sizeOfTaskList) throws DukeException {
        String[] parsedCommand = userCommand.split(" ", 2);
        parsedCommand[0] = parsedCommand[0].trim();

        switch (parsedCommand[0]) {
        case "hi":
        case "list":
        case "bye":
            return new ParsedCommand(parsedCommand[0]);
        case "mark":
        case "unmark":
        case "delete":
        case "recur":
            if (parsedCommand.length <= 1) {
                throw new DukeException();
            }
            ParsedCommand fullyParsedCommand = parseIntegerInput(
                    parsedCommand[0], parsedCommand[1].trim(), sizeOfTaskList);
            if (fullyParsedCommand == null) {
                throw new DukeException();
            }
            return fullyParsedCommand;
        case "find":
            return new ParsedCommand(parsedCommand[0], parsedCommand[1]);
        case "todo":
        case "event":
        case "deadline":
            try {
                ArrayList<String> parsedTaskInput = parseTaskInput(parsedCommand[0], parsedCommand[1]);
                ParsedCommand processedCommand = createParseCommandFromList(parsedTaskInput);
                return processedCommand;
            } catch (DukeException error) {
                throw new DukeException();
            }
        default:
            throw new DukeException();
        }
    }

    /**
     * Parse input where an integer is expected.
     *
     * @param command String that contains the command word
     * @param index String that contains the index
     * @param lengthOfTaskList Length of the arrayList supplied.
     * @return ParsedCommand with the right command and input, if not return null.
     */
    private static ParsedCommand parseIntegerInput(String command, String index, Integer lengthOfTaskList) {
        try {
            Integer taskIndex = Integer.parseInt(index) - 1;
            if (taskIndex >= 0 && taskIndex < lengthOfTaskList) {
                return new ParsedCommand(command, taskIndex);
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

    private static ArrayList<String> parseTaskInput(String command, String otherData) throws DukeException {
        switch (command) {
        case "todo":
            ArrayList<String> outputForTodo = new ArrayList<String>();
            outputForTodo.add(command);
            outputForTodo.add(otherData);
            return outputForTodo;
        case "event":
        case "deadline":
            ArrayList<String> outputForTaskWithDate = new ArrayList<String>();
            outputForTaskWithDate.add(command);
            if (!otherData.contains("/by")) {
                throw new DukeException();
            }
            String[] temp = otherData.split("/by", 2);
            String task = temp[0].trim();
            outputForTaskWithDate.add(task);
            if (temp[1].contains("/recur")) {
                String[] dateAndPeriod = temp[1].split("/recur", 2);
                String date = dateAndPeriod[0].trim();
                outputForTaskWithDate.add(date);
                String recurPeriod = dateAndPeriod[1].trim();
                outputForTaskWithDate.add(recurPeriod);
            } else {
                String date = temp[1].trim();
                outputForTaskWithDate.add(date);
            }
            return outputForTaskWithDate;
        default:
            throw new DukeException();
        }
    }

    private static ParsedCommand createParseCommandFromList(ArrayList<String> inputArrayList) throws DukeException {
        switch (inputArrayList.get(0)) {
        case "todo":
            return new ParsedCommand(inputArrayList.get(0), inputArrayList.get(1));
        case "event":
        case "deadline":
            try {
                LocalDate date = parseDateInput(inputArrayList.get(2), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                if (inputArrayList.size() == 3) {
                    return new ParsedCommand(inputArrayList.get(0), inputArrayList.get(1), date);
                } else {
                    assert inputArrayList.size() == 4 : "Recurring event should have 4 inputs";
                    RecurPeriod period = RecurPeriod.createRecurPeriod(inputArrayList.get(3).trim());
                    return new ParsedCommand(inputArrayList.get(0), inputArrayList.get(1),
                            date, period);
                }
            } catch (DukeException error) {
                throw new DukeException();
            }
        default:
            throw new DukeException();
        }
    }

    /**
     * Creates Task from the data given as a String.Data is in the form of task.toString().
     *
     * @param data String that represents the Task.
     * @return Task that represents the String argument.
     * @throws DukeException If data is an event or deadline and does not have a valid date.
     */
    public static Task parseDataToGetTask(String data) throws DukeException {
        String[] processedData = data.trim().split("]", 3);
        if (processedData[0].contains("T")) {
            Task task = new ToDo(processedData[2].trim());
            if (processedData[1].contains("X")) {
                task.setDone(true);
            }
            return task;
        }

        String[] taskDescriptionAndDueDate = processedData[2].split("\\(by:", 2);
        String dateString = taskDescriptionAndDueDate[1].replace(")", "");
        String taskDescription = taskDescriptionAndDueDate[0].trim();

        try {
            LocalDate date = Parser.parseDateInput(dateString.trim(), DateTimeFormatter.ofPattern("MMM dd yyyy"));
            RecurPeriod period = Parser.parseDataToGetRecurPeriod(taskDescription);
            String taskDescriptionWithoutRecurPeriod = Parser.parseDataToDescription(taskDescription);
            ArrayList tasksInfo = new ArrayList<>();
            tasksInfo.add(processedData[0].trim());
            tasksInfo.add(taskDescriptionWithoutRecurPeriod);
            tasksInfo.add(processedData[1].trim());
            Task task = Parser.createTaskFromData(tasksInfo, date, period);

            return task;
        } catch (DukeException error) {
            throw new DukeException();
        }
    }

    public static RecurPeriod parseDataToGetRecurPeriod(String str) throws DukeException {
        try {
            if (str.contains("every")) {
                String[] descriptionAndRecurPeriod = str.split("every");
                return RecurPeriod.createRecurPeriod(descriptionAndRecurPeriod[1].trim());
            } else {
                return RecurPeriod.createRecurPeriod("");
            }
        } catch (DukeException error) {
            throw new DukeException();
        }
    }

    /**
     * Creates task based on the input given
     *
     * @param tasksInfo An ArrayList of String with 3 elements, in the order of taskIndication,
     *                   taskDescription and markIndication.
     * @param date       Due date of the task.
     * @throws DukeException When task cannot be created.
     */
    private static Task createTaskFromData(ArrayList<String> tasksInfo, LocalDate date, RecurPeriod period) throws DukeException {
        if (tasksInfo.size() != 3) {
            throw new DukeException();
        }

        String taskIndication =  tasksInfo.get(0);
        String taskDescription = tasksInfo.get(1);
        String markIndication = tasksInfo.get(2);

        Task taskToBeReturned;
        if (taskIndication.contains("E")) {
            taskToBeReturned = new Event(taskDescription, date, period);
        } else if (taskIndication.contains("D")) {
            taskToBeReturned = new Deadline(taskDescription, date, period);
        } else {
            throw new DukeException();
        }

        if (markIndication.contains("X")) {
            taskToBeReturned.setDone(true);
        }

        return taskToBeReturned;
    }

    public static String parseDataToDescription(String str) {
        if (str.contains("every")) {
            String[] descriptionAndRecurPeriod = str.split("every");
            return descriptionAndRecurPeriod[0].trim();
        } else {
            return str.trim();
        }
    }
}
