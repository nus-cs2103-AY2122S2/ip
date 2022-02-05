package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents a parser to format user input
 */
public class Parser {

    static final int COMMAND_INDEX = 0;
    static final int DESCRIPTION_INDEX = 1;
    static final int TIME_INDEX = 2;

    static final int TIME_INDEX_CORRECTION = 4;

    /**
     * Returns Command enum of string that user entered
     *
     * @param command String that needs to be converted to Command enum
     * @return Command enum of command to execute
     * @throws CommandNotFoundException when command is not found
     */
    public static Command parseCommand(String command) throws CommandNotFoundException {
        command = command.toLowerCase();
        switch (command) {
        case "list":
            return Command.LIST;
        case "mark":
            return Command.MARK;
        case "unmark":
            return Command.UNMARK;
        case "event":
            return Command.EVENT;
        case "deadline":
            return Command.DEADLINE;
        case "todo":
            return Command.TODO;
        case "bye":
            return Command.BYE;
        case "delete":
            return Command.DELETE;
        case "find":
            return Command.FIND;
        default:
            throw new CommandNotFoundException("Unrecognised Command");
        }
    }

    /**
     * Returns String array of formatted input
     * Index 0: Command
     * Index 1: Description of task
     * Index 2: Time
     * Time will be an empty string if not provided
     *
     * @param input String to be formatted
     * @return String array of formatted input
     */
    public static String[] parseInput(String input) {
        String[] output = new String[3];
        String[] inputs = input.split(" ", 2);
        output[COMMAND_INDEX] = inputs[0];
        if (inputs.length == 1) {
            return output;
        }

        String taskDetails = inputs[1];
        if (taskDetails.contains("/by")) {
            int indexOfTime = taskDetails.indexOf("/by");
            output[DESCRIPTION_INDEX] = taskDetails.substring(0, indexOfTime);
            output[TIME_INDEX] = taskDetails.substring(indexOfTime + TIME_INDEX_CORRECTION);
        } else if (taskDetails.contains("/at")) {
            int indexOfTime = taskDetails.indexOf("/at");
            output[DESCRIPTION_INDEX] = taskDetails.substring(0, indexOfTime);
            output[TIME_INDEX] = taskDetails.substring(indexOfTime + TIME_INDEX_CORRECTION);
        } else {
            output[DESCRIPTION_INDEX] = taskDetails;
            output[TIME_INDEX] = "";
        }
        return output;
    }

    /**
     * Parses string into TaskType enum
     *
     * @param taskType String indicating type of task
     * @return TaskType enum indicating type of task
     */
    public static TaskType parseTaskType(String taskType) {
        taskType = taskType.toLowerCase();
        switch (taskType) {
        case "todo":
            return TaskType.TODO;
        case "event":
            return TaskType.EVENT;
        case "deadline":
            return TaskType.DEADLINE;
        default:
            return null;
        }
    }

    /**
     * Parses string into LocalDateTime
     *
     * @param time String representing DateTime
     * @return LocalDateTime representing time entered as string
     */
    public static LocalDateTime parseTime(String time) {
        try {
            return LocalDateTime.parse(time);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

}
