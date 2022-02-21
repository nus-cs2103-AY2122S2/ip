package duke.command;

import duke.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import java.util.Arrays;


/**
 * This class parses user input from Duke.
 * It can recognise 8 commands and deals with invalid user input.
 *
 * @author Jian Rong
 */
public class Parser {

    /**
     * Calls the appropriate method based on user input, or handles invalid user input.
     *
     * @param input Input comes from user
     * @param taskList TaskList is the list of tasks that was created in Duke
     * @return boolean Returns false if user says "bye", returns true otherwise
     */
    public static String parseInput(String input, TaskList taskList) {
        assert taskList != null;
        String[] inputArray = input.split(" ");
        String[] tempArray = Arrays.copyOfRange(inputArray, 1, inputArray.length);
        String[] command = {inputArray[0], String.join(" ", tempArray)};
        try {
            switch (command[0]) {
            case ("list"):
                return taskList.listItem();
            case ("mark"):
                return taskList.markItem(command);
            case ("unmark"):
                return taskList.unmarkItem(command);
            case ("delete"):
                return taskList.deleteItem(command);
            case ("todo"):
            case ("deadline"):
            case ("event"):
                return parseAddItem(command, taskList);
            case ("find"):
                taskList.findItem(command[1]);
                break;
            default:
                throw new DukeException("Sorry I don't understand that command");
            }
        } catch (DukeException e) {
            return e.getMessage();
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            return("This index doesn't exist in list");
        }
        return "";
    }

    private static String parseAddItem(String[] command, TaskList taskList) throws DukeException {
        assert taskList!= null;
        StringBuilder result = new StringBuilder();
        switch (command[0]) {
        case "todo":
            if (command[1].isEmpty()) {
                throw new DukeException("Please use this format: todo <Activity>");
            }
            result.append(taskList.addTodo(command[1]));
            break;
        case "deadline":
            try {
                String title = command[1].split(" /by ")[0];
                String deadline = command[1].split(" /by ")[1];
                String[] deadlineList = deadline.split(" ");
                LocalDate date = LocalDate.parse(deadlineList[0].replace("/", "-"));
                LocalTime time = LocalTime.parse(deadlineList[1]);
                result.append(taskList.addDeadline(title, date, time));
            } catch (IndexOutOfBoundsException | DateTimeParseException e) {
                throw new DukeException("Please tell me the deadline in this format: deadline <Activity> /by YYYY/MM/DD HH:MM");
            }
            break;
        case "event":
            try {
                String title = command[1].split(" /at ")[0];
                String deadline = command[1].split(" /at ")[1];
                String[] deadlineList = deadline.split(" ");
                LocalDate date = LocalDate.parse(deadlineList[0].replace("/", "-"));
                LocalTime time = LocalTime.parse(deadlineList[1]);
                result.append(taskList.addEvent(title, date, time));
            } catch (IndexOutOfBoundsException | DateTimeParseException e) {
                throw new DukeException("Please tell me the deadline in this format: event <Activity> /at YYYY/MM/DD HH:MM");
            }
            break;
        }
        result.append(String.format("You have %d tasks in your list\n", taskList.getSize()));
        return String.valueOf(result);
    }


}
