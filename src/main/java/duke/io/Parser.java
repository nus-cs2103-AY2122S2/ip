package duke.io;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.exception.DukeException;
import duke.exception.DukeNoTimeSpecifiedException;
import duke.exception.DukeWrongTimeFormatException;

/**
 * This is a Parser class that handles user's string input and re-format it for Duke to understand.
 */
public class Parser {

    /**
     * This method is used when loading file from "duke.txt" to parse in all the local saved tasks.
     *
     * @param input String input read from the txt file.
     * @return Return a UserInput for Duke to process subsequent actions.
     */
    public static UserInput parseTask(String input) {
        char typeOfTask = input.charAt(1);
        boolean isTaskDone = input.charAt(4) == 'X';
        int descriptionStart = 6; // Format: [<typeOfTask>][<isTaskDone>] <DescriptionStart> ...

        if (typeOfTask == 'T') { // type todo
            return parseTaskTypeT(descriptionStart, input, isTaskDone);

        } else if (typeOfTask == 'D') { // type "deadline"
            return parseTaskTypeD(descriptionStart, input, isTaskDone);

        } else if (typeOfTask == 'E') { // type event
            return parseTaskTypeE(descriptionStart, input, isTaskDone);
        } else {
            return null;
        }
    }

    private static UserInput parseTaskTypeT(int descriptionStart, String input, boolean isTaskDone) {
        String description = input.substring(descriptionStart);
        description = description.replaceFirst(" ", "");
        return new UserInput("T", "todo", description, isTaskDone);
    }

    private static UserInput parseTaskTypeD(int descriptionStart, String input, boolean isTaskDone) {
        int timeStart = input.indexOf("(by:");
        String description = input.substring(descriptionStart, timeStart);
        description = description.replaceFirst(" ", "");
        String time = input.substring(timeStart + 1, input.length() - 1);
        time = time.replaceFirst(":", "");
        return new UserInput("D", "deadline", description, time, isTaskDone);
    }

    private static UserInput parseTaskTypeE(int descriptionStart, String input, boolean isTaskDone) {
        int timeStart = input.indexOf("(at:");
        String description = input.substring(descriptionStart, timeStart);
        description = description.replaceFirst(" ", "");
        String time = input.substring(timeStart + 1, input.length() - 1);
        time = time.replaceFirst(":", "");

        return new UserInput("E", "event", description, time, isTaskDone);
    }

    /**
     * This method is used to parse the user's input into Duke.
     *
     * @param input String input from the user.
     * @return Return a UserInput for Duke to process subsequent actions.
     * @throws DukeWrongTimeFormatException If the user's input has invalid time format for certain type of task.
     */
    public UserInput parseInput(String input) throws DukeException {
        UserInput userInput = new UserInput();
        int descriptionStart = input.indexOf(' ');
        int timeStart = input.indexOf('/');
        String command;

        // if it is a single word input
        if (descriptionStart == -1) {
            command = input;
            userInput.setCommand(command);
            return userInput;
        }

        // else process input based on the command type
        command = input.substring(0, descriptionStart);

        switch (command) {
        case "todo":
            // process the input as ToDoTask
            userInput = parseTodoInput(input, descriptionStart);
            break;

        case "deadline":
            // process the input as DeadlineTask
            userInput = parseDeadlineInput(input, descriptionStart, timeStart);
            break;

        case "event":
            // process the input as EventTask
            userInput = parseEventInput(input, descriptionStart, timeStart);
            break;

        default:
            // process the input for other types of command (e.g. find, list, delete, mark, unmark)
            String description = input.substring(descriptionStart);
            userInput.setCommand(command);
            userInput.setDescription(description);
            break;
        }

        return userInput;
    }

    private UserInput parseTodoInput(String input, int descriptionStart) {
        String description = input.substring(descriptionStart);
        description = description.replaceFirst(" ", "");

        return new UserInput("T", "todo", description, false);
    }

    private UserInput parseDeadlineInput(String input, int descriptionStart, int timeStart) throws DukeException {
        if (timeStart == -1) {
            throw new DukeNoTimeSpecifiedException("OOPS!!! Remember to set a time.");
        }

        String description = input.substring(descriptionStart, timeStart);
        String time = input.substring(timeStart);

        description = description.replaceFirst(" ", "");
        time = time.replaceFirst("/", "");

        // check if user's deadline input is a valid format
        try {
            LocalDate date = LocalDate.parse(time.replaceFirst("by ", ""));
            time = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            time = "by " + time;

        } catch (DateTimeException e) {
            throw new DukeWrongTimeFormatException("OOPS!!! Invalid deadline format! (yyyy-mm-dd)");
        }

        return new UserInput("D", "deadline", description, time, false);
    }

    private UserInput parseEventInput(String input, int descriptionStart, int timeStart) throws DukeException {
        if (timeStart == -1) {
            throw new DukeNoTimeSpecifiedException("OOPS!!! Remember to set a time.");
        }

        String description = input.substring(descriptionStart, timeStart);
        String time = input.substring(timeStart);

        description = description.replaceFirst(" ", "");
        time = time.replaceFirst("/", "");

        // check if user's day input is a valid format
        try {
            boolean isDayOfWeek = false;
            String day = time.replaceFirst("at ", "");
            day = day.toUpperCase();

            for (DayOfWeek d : DayOfWeek.values()) {
                if(day.equals(d.toString())) {
                    isDayOfWeek = true;
                }
            }
            if (!isDayOfWeek) {
                throw new DateTimeException("This is not a valid day input");
            }

        } catch (DateTimeException e) {
            throw new DukeWrongTimeFormatException("OOPS!!! Invalid day format! (e.g. Monday)");
        }

        return new UserInput("E", "event", description, time, false);
    }
}
