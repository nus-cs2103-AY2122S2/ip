package holobot.misc;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

import holobot.exception.HoloBotException;
import holobot.exception.InvalidCommand;
import holobot.exception.InvalidDateTime;
import holobot.exception.InvalidIndex;

/**
 * Extracts and deciphers the user's input, and subsequently calls the relevant function.
 * Type of inputs that are currently supported:
 * list/todo/deadline/event/mark/unmark/delete/bye
 *
 * @author Terng Yan Long
 */
public class Parser {
    /**
     * Calls different functions according to the user's input command.
     * Any command that is not in the correct form will be throw an "error" message.
     *
     * @param userInput Command entered by the user.
     */
    public static String parse(String userInput, TaskList listOfTasks) throws HoloBotException {
        List<String> wordsList = separateInput(userInput);
        String firstWord = wordsList.get(0);

        switch (firstWord) {
        case ("list"):
            if (wordsList.size() == 1) {
                return listOfTasks.display();
            } else {
                throw new InvalidCommand("This command should not have any arguments :(");
            }
        case ("todo"):
            if (wordsList.size() > 1) {
                return listOfTasks.todo(userInput.substring(5));
            } else {
                throw new InvalidCommand("The description of a todo cannot be empty :(");
            }
        case ("deadline"):
            if (wordsList.size() < 4) {
                throw new InvalidCommand("Incorrect number of arguments supplied :(");
            } else if (!wordsList.contains("/by")) {
                throw new InvalidCommand("Please follow this format: deadline <task> "
                        + "/by <date in yyyy-MM-dd> <time in 24hrs format>");
            } else {
                return createDeadline(wordsList, listOfTasks);
            }
        case ("event"):
            if (wordsList.size() < 4) {
                throw new InvalidCommand("Incorrect number of arguments supplied :(");
            } else if (!wordsList.contains("/at")) {
                throw new InvalidCommand("Please follow this format: event <task> "
                        + "/at <date in yyyy-MM-dd> <time in 24hrs format>");
            } else {
                return createEvent(wordsList, listOfTasks);
            }
        case("mark"):
            if (wordsList.size() != 2) {
                throw new InvalidCommand("This command should have exactly 1 argument.");
            } else if (!isInteger(wordsList.get(1))) {
                throw new InvalidCommand("The argument MUST contain a single integer.");
            } else {
                return markTask(wordsList, listOfTasks);
            }
        case("unmark"):
            if (wordsList.size() != 2) {
                throw new InvalidCommand("This command should have exactly 1 argument.");
            } else if (!isInteger(wordsList.get(1))) {
                throw new InvalidCommand("The argument MUST contain a single integer.");
            } else {
                return unmarkTask(wordsList, listOfTasks);
            }

        case("delete"):
            if (wordsList.size() != 2) {
                throw new InvalidCommand("This command should have exactly 1 argument.");
            } else if (!isInteger(wordsList.get(1))) {
                throw new InvalidCommand("The argument MUST contain a single integer.");
            } else {
                return deleteTask(wordsList, listOfTasks);
            }
        case ("find"):
            if (wordsList.size() > 1) {
                return listOfTasks.find(userInput.substring(5));
            } else {
                throw new InvalidCommand("The search field cannot be empty :(");
            }
        case ("schedule"):
            if (wordsList.size() == 2) {
                return listOfTasks.schedule(wordsList.get(1));
            } else {
                throw new InvalidCommand("The argument MUST contain a single date.");
            }
        case ("bye"):
            return "See you again, peko!";
        default:
            throw new InvalidCommand("I don't know what that means :(");
        }
    }

    /**
     * Converts the user input string into a List of strings, partitioned by a whitespace character.
     *
     * @param userInput String specified by the user.
     * @return A list of Strings.
     */
    private static List<String> separateInput(String userInput) {
        String[] wordsArray = userInput.trim().split(" ");
        return Arrays.asList(wordsArray);
    }

    /**
     * Creates a new Deadline Task.
     *
     * @param wordsList User input.
     * @param listOfTasks Current TaskList.
     * @return Success Message after the Deadline task has been added to the TaskList.
     */
    private static String createDeadline(List<String> wordsList, TaskList listOfTasks) {
        int separator = wordsList.indexOf("/by");
        String desc = "";
        String dateTime = "";
        for (int i = 1; i < separator; i++) {
            desc += wordsList.get(i);
            desc += " ";
        }
        for (int i = separator + 1; i < wordsList.size(); i++) {
            dateTime += wordsList.get(i);
            dateTime += " ";
        }

        dateTime = removeLastChar(dateTime);
        String[] dateTimeArray = dateTime.split(" ");
        if (dateTimeArray.length > 2 || dateTimeArray.length < 1) {
            throw new InvalidCommand("Incorrect number of arguments supplied :(");
        }

        // Parse user input into LocalDate/LocalTime if it is in the correct format.
        LocalDate newDate = convertDate(dateTimeArray[0]);
        LocalTime newTime = null;
        if (dateTimeArray.length > 1) { // Optional time input
            newTime = convertTime(dateTimeArray[1]);
        }

        DateTimeChecker.checkDateTime(newDate, newTime);

        return listOfTasks.deadline(removeLastChar(desc), newDate, newTime);
    }

    /**
     * Creates a new Deadline Task.
     *
     * @param wordsList User input.
     * @param listOfTasks Current TaskList.
     * @return Success Message after the Event task has been added to the TaskList.
     */
    private static String createEvent(List<String> wordsList, TaskList listOfTasks) {
        int separator = wordsList.indexOf("/at");
        String desc = "";
        String dateTime = "";
        for (int i = 1; i < separator; i++) {
            desc += wordsList.get(i);
            desc += " ";
        }
        for (int i = separator + 1; i < wordsList.size(); i++) {
            dateTime += wordsList.get(i);
            dateTime += " ";
        }
        dateTime = removeLastChar(dateTime);
        String[] dateTimeArray = dateTime.split(" ");
        if (dateTimeArray.length > 3 || dateTimeArray.length < 1) {
            throw new InvalidCommand("Incorrect number of arguments supplied :(");
        }
        // Parse user input into LocalDate/LocalTime if it is in the correct format.
        LocalDate newDate = convertDate(dateTimeArray[0]);
        LocalTime newStartTime = null;
        LocalTime newEndTime = null;
        if (dateTimeArray.length > 1) { // Optional start time input
            newStartTime = convertTime(dateTimeArray[1]);
        }
        if (dateTimeArray.length > 2) { // Optional end time input
            newEndTime = convertTime(dateTimeArray[2]);
        }
        DateTimeChecker.checkDateTime(newDate, newStartTime, newEndTime);
        return listOfTasks.event(removeLastChar(desc), newDate, newStartTime, newEndTime);
    }

    /**
     * Marks a task if the index specified is valid.
     *
     * @param wordsList User input.
     * @param listOfTasks Current TaskList.
     * @return Success Message after task has been marked.
     */
    private static String markTask(List<String> wordsList, TaskList listOfTasks) {
        int currTaskId = Integer.parseInt(wordsList.get(1));
        if (currTaskId > 0 & currTaskId <= listOfTasks.getNumberOfTasks()) {
            return listOfTasks.mark(currTaskId); // Valid taskID, proceed to mark task
        } else {
            throw new InvalidIndex("The specified task ID is out of range. "
                    + "Please enter a number from 0 to " + listOfTasks.getNumberOfTasks() + ".");
        }
    }

    /**
     * Unmarks a task if the index specified is valid.
     *
     * @param wordsList User input.
     * @param listOfTasks Current TaskList.
     * @return Success Message after task has been unmarked.
     */
    private static String unmarkTask(List<String> wordsList, TaskList listOfTasks) {
        int currTaskId = Integer.parseInt(wordsList.get(1));
        if (currTaskId > 0 & currTaskId <= listOfTasks.getNumberOfTasks()) {
            return listOfTasks.unmark(currTaskId); // Valid taskID, proceed to unmark task
        } else {
            throw new InvalidIndex("The specified task ID is out of range. "
                    + "Please enter a number from 0 to " + listOfTasks.getNumberOfTasks() + ".");
        }
    }

    /**
     * Deletes a task if the index specified is valid.
     *
     * @param wordsList User input.
     * @param listOfTasks Current TaskList.
     * @return Success Message after task has been deleted.
     */
    private static String deleteTask(List<String> wordsList, TaskList listOfTasks) {
        int currTaskId = Integer.parseInt(wordsList.get(1));
        if (currTaskId > 0 & currTaskId <= listOfTasks.getNumberOfTasks()) {
            return listOfTasks.delete(currTaskId); // Valid taskID, proceed to unmark task
        } else {
            throw new InvalidIndex("The specified task ID is out of range. "
                    + "Please enter a number from 0 to " + listOfTasks.getNumberOfTasks() + ".");
        }
    }

    /**
     * Tests if the input string represents an integer value.
     *
     * @param input Target string.
     * @return true if the input string represents an integer value, and false otherwise.
     * @throws NumberFormatException thrown if the string does not represent an integer value.
     */
    private static boolean isInteger(String input) throws NumberFormatException {
        boolean isInt = true;
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            isInt = false;
        }
        return isInt;
    }

    /**
     * Removes the last character of a string.
     *
     * @param str Target String.
     * @return New string after removing the last character of the original string.
     */
    private static String removeLastChar(String str) {
        if (str == null || str.length() == 0) {
            return "";
        } else {
            return str.substring(0, str.length() - 1);
        }
    }

    /**
     * Checks if the input date is in the correct format.
     *
     * @param date Date to be tested.
     */
    private static LocalDate convertDate(String date) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate convertedDate;
        try {
            convertedDate = LocalDate.parse(date, format);
        } catch (DateTimeParseException | NullPointerException e) {
            throw new InvalidDateTime("Please enter date in this format: <yyyy-MM-dd>");
        }
        return convertedDate;
    }

    /**
     * Checks if the input date is in the correct format.
     *
     * @param time Time to be tested.
     */
    private static LocalTime convertTime(String time) {
        if (time == null) { // Time is optional
            return null;
        }
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HHmm");
        LocalTime convertedTime;
        try {
            convertedTime = LocalTime.parse(time, format);
        } catch (DateTimeParseException | NullPointerException e) {
            throw new InvalidDateTime("Please enter time in this 24hrs-format: <HHmm>");
        }
        return convertedTime;
    }
}
