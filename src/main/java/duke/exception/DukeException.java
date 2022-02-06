package duke.exception;

import java.util.regex.Pattern;

import duke.task.Tasklist;

/**
 * Returns a custom exception used to handle edge cases in our application.
 */
public class DukeException extends Exception {

    /**
     * Returns an error object with a custom message.
     *
     * @param message This is the error message returned to users.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Checks whether the input is valid for Deadline and Event tasks. Otherwise, throws an exception.
     *
     * @param index Index of the keyword '/by' or '/at' that is too be used in the tasks.
     * @param input The string that the user has entered following the command.
     * @param taskType Specifies the task type of either Deadline or Event.
     * @param list Tasklist that contains all tasks.
     * @return An array of the date and time format, if specified.
     * @throws DukeException If any of the params do not satisfy conditions for processing.
     */
    public static String[] isTaskValid(int index, String input, String taskType, Tasklist list) throws DukeException {
        if (taskType.equals("event")) {
            if (index == -1) {
                throw new DukeException("Please specify a date using '/at'.\n");
            }
            if (index - 1 < 6 || input.substring(6, index - 1).strip().equals("")) {
                throw new DukeException("Please key in a valid task name.\n");
            }
            DukeException.isDuplicatedTask(input.substring(6, index - 1).strip(), list);
        } else if (taskType.equals("deadline")){
            if (index == -1) {
                throw new DukeException("Please specify a date using '/by'.\n");
            }
            if (index - 1 < 9 || input.substring(9, index - 1).strip().equals("")) {
                throw new DukeException("Please key in a valid task name.\n");
            }
            DukeException.isDuplicatedTask(input.substring(9, index - 1).strip(), list);
        } else {
            throw new DukeException("Please use 'todo, deadline, event' to start adding tasks.\n");
        }
        if (index + 4 > input.length() || input.substring(index + 4).strip().equals("")) {
            throw new DukeException("Please key in a valid time.\n");
        }
        if (input.contains("|")) {
            throw new DukeException("Sorry, avoid using '|' as it is a special character.\n");
        }
        String timeString = input.substring(index + 4);
        String[] timeArray = timeString.split(" ");
        if (timeArray.length == 0 || timeArray.length > 2) {
            throw new DukeException("Please provide time in the format 'DD/MM/YYYY <time>'.\n");
        }
        String[] dateArray = timeArray[0].split("/");
        if (dateArray.length != 3) {
            throw new DukeException("Please provide time in the format 'DD/MM/YYYY <time>'.\n");
        }
        String regex = "[0-9]+";
        Pattern p = Pattern.compile(regex);
        for (int i = 0; i < 3; i++) {
            if (!p.matcher(dateArray[i]).matches()) {
                throw new DukeException("Please provide time in the format 'DD/MM/YYYY <time>'.\n");
            }
        }
        if (timeArray.length == 1) {
            return dateArray;
        } else {
            return new String[]{dateArray[0], dateArray[1], dateArray[2], timeArray[1]};
        }
    }

    /**
     * Checks whether the input is valid for Todo tasks. Otherwise, throws an exception.
     *
     * @param input The string that the user has entered following the command.
     * @param list Tasklist that contains all tasks.
     * @throws DukeException If any of the params do not satisfy conditions for processing.
     */
    public static void isTaskValid(String input, Tasklist list) throws DukeException {
        if (input.length() < 5 || input.substring(5).strip().equals("")) {
            throw new DukeException("Please key in a valid task name.\n");
        }
        if (input.contains("|")) {
            throw new DukeException("Sorry, avoid using '|' as it is a special character.\n");
        }
        DukeException.isDuplicatedTask(input.substring(5).strip(), list);
    }

    /**
     * Returns the integer if input is valid. Otherwise, throws an exception.
     *
     * @param input The string that the user has entered following the command.
     * @param list Tasklist that contains all tasks.
     * @return The index to be used.
     * @throws DukeException If any of the params do not satisfy conditions for processing.
     */
    public static int isIndexValid(String input, Tasklist list) throws DukeException {
        String [] keywords = input.split(" ");
        int index;
        if (keywords.length != 2) {
            throw new DukeException("Please provide a single task number.\n");
        }
        try {
            index = Integer.parseInt(keywords[1]) - 1;
            if (index < 0) {
                throw new DukeException("Please key in a number starting from 1.\n");
            }
            if (index >= list.getTotalTasks()) {
                throw new DukeException("No task with this number exists yet!\n");
            }
        } catch (NumberFormatException err) {
            throw new DukeException("Please key in a valid digit.\n");
        }
        return index;
    }

    /**
     * Returns the keyword if input is valid. Otherwise, throws an exception.
     *
     * @param input The string that the user has entered following the command.
     * @return The word to be used.
     * @throws DukeException If any of the params do not satisfy conditions for processing.
     */
    public static String isWordValid(String input) throws DukeException {
        if (input.length() < 6) {
            throw new DukeException("Please type in a word to search through the tasks.\n");
        }
        return input.substring(5);
    }

    /**
     * Checks whether the input has a similar name as one of the existing tasks.
     *
     * @param input The string that the user has entered following the command.
     * @param list Tasklist that contains all tasks.
     * @throws DukeException If any of the params do not satisfy conditions for processing.
     */
    public static void isDuplicatedTask(String input, Tasklist list) throws DukeException {
        for (int i = 0; i < list.getTotalTasks(); i++) {
            if (list.getTask(i).getTaskName().equals(input)) {
                throw new DukeException("Sorry, a task with a similar name already exists.\n");
            }
        }
    }
}
