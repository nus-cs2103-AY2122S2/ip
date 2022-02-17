package mnsky.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mnsky.exceptions.MnskyException;
import mnsky.exceptions.MnskyMissingParameterException;

public class Parser {
    private static final int MARK_INDEX = 4;
    private static final int MIN_TASK_CMD_LENGTH = 2;
    private static final int MIN_INDEX_CMD_LENGTH = 2;
    private static final int MIN_FIND_CMD_LENGTH = 2;
    /**
     * Retrieves and returns the value of the index parameter from the input.
     * @param command The name of the command that called this function.
     * @param inputSplit The input, split into an array using space.
     * @return The value of the index parameter.
     * @throws MnskyMissingParameterException If the index parameter is missing.
     */
    private static String retrieveIndex(String command, String[] inputSplit) throws MnskyMissingParameterException {
        if (inputSplit.length < MIN_INDEX_CMD_LENGTH) {
            throw new MnskyMissingParameterException(command, "index");
        }

        return inputSplit[MIN_INDEX_CMD_LENGTH - 1];
    }

    /**
     * Creates a new task by parsing the input.
     * @param input The input string.
     * @return The new task.
     * @throws MnskyMissingParameterException If the name parameter is missing.
     */
    private static ArrayList<String> parseTask(String input) throws MnskyMissingParameterException {
        String[] inputSplit = input.split(" ", 2);
        if (inputSplit.length < MIN_TASK_CMD_LENGTH) {
            throw new MnskyMissingParameterException("todo", "name");
        }

        return new ArrayList<>(List.of("todo", inputSplit[MIN_TASK_CMD_LENGTH - 1], ""));
    }

    /**
     * Creates a new deadline (a task with a "by" parameter included) by parsing the input.
     * @param inputSplit The input, split into an array using space.
     * @return The new deadline.
     * @throws MnskyMissingParameterException Thrown if the name or the by parameter is missing.
     */
    private static ArrayList<String> parseDeadline(String[] inputSplit) throws MnskyMissingParameterException {
        if (inputSplit.length < MIN_TASK_CMD_LENGTH) {
            throw new MnskyMissingParameterException("deadline", "name");
        }

        int byIndex = 1;
        for (; byIndex < inputSplit.length; byIndex++) {
            if (inputSplit[byIndex].equals("/by")) {
                break;
            }
        }

        if (byIndex >= inputSplit.length) {
            throw new MnskyMissingParameterException("deadline", "by");
        }

        String[] deadlineNameSplit = Arrays.copyOfRange(inputSplit, 1, byIndex);
        String[] bySplit = Arrays.copyOfRange(inputSplit, byIndex + 1, inputSplit.length);

        String deadlineName = String.join(" ", deadlineNameSplit);
        String by = String.join(" ", bySplit);

        return new ArrayList<>(List.of("deadline", deadlineName, by));
    }

    /**
     * Creates a new event (a task with an "at" parameter included) by parsing the input.
     * @param inputSplit The input, split into an array using space.
     * @return The new event.
     * @throws MnskyMissingParameterException If the name or the at parameter is missing.
     */
    private static ArrayList<String> parseEvent(String[] inputSplit) throws MnskyMissingParameterException {
        if (inputSplit.length < 2) {
            throw new MnskyMissingParameterException("event", "name");
        }

        int atIndex = 1;
        for (; atIndex < inputSplit.length; atIndex++) {
            if (inputSplit[atIndex].equals("/at")) {
                break;
            }
        }

        if (atIndex >= inputSplit.length) {
            throw new MnskyMissingParameterException("event", "at");
        }

        String[] eventNameSplit = Arrays.copyOfRange(inputSplit, 1, atIndex);
        String[] atSplit = Arrays.copyOfRange(inputSplit, atIndex + 1, inputSplit.length);

        String eventName = String.join(" ", eventNameSplit);
        String at = String.join(" ", atSplit);

        return new ArrayList<>(List.of("event", eventName, at));
    }

    private static String retrieveSearchTerm(String input) {
        String[] inputSplit = input.split(" ", 2);
        if (inputSplit.length < MIN_FIND_CMD_LENGTH) {
            throw new MnskyMissingParameterException("find", "search_term");
        }

        return inputSplit[MIN_FIND_CMD_LENGTH - 1];
    }

    /**
     * Parses all the tasks in the storage data.
     * @param rawTaskList The raw strings of the task list from the storage data.
     * @return A parsed version of the strings of the task list from the storage data.
     * @throws MnskyException If an exception occurs with parsing the raw task list.
     */
    public static ArrayList<ArrayList<String>> parseStorageData(ArrayList<String> rawTaskList) throws MnskyException {
        try {
            ArrayList<ArrayList<String>> tasks = new ArrayList<>();

            for (String line : rawTaskList) {
                String[] lineSplit = line.split(" ");
                ArrayList<String> nextTask = null;
                assert line.length() >= 1;

                if (line.charAt(1) == 'T') {
                    nextTask = parseTask(line);
                } else if (line.charAt(1) == 'D') {
                    nextTask = parseDeadline(lineSplit);
                } else if (line.charAt(1) == 'E') {
                    nextTask = parseEvent(lineSplit);
                }

                if (nextTask != null) {
                    nextTask.add(line.substring(MARK_INDEX, MARK_INDEX + 1));
                    tasks.add(nextTask);
                }
            }

            return tasks;
        } catch (MnskyMissingParameterException e) {
            throw new MnskyException("[MNSKY is having trouble parsing the storage data file...]\n");
        }
    }

    /**
     * Parses the input and executes the logic depending on the type of input.
     * @return True if the user input "bye" and thus wants to stop talking to the chatbot.
     *          False otherwise.
     */
    public static ArrayList<String> parseInput(String input) throws MnskyException {
        String[] inputSplit = input.split(" ");
        ArrayList<String> parsedInput = new ArrayList<>();
        assert inputSplit.length >= 1;
        parsedInput.add(inputSplit[0]);

        switch (inputSplit[0]) {
        case "mark":
            parsedInput.add(retrieveIndex("mark", inputSplit));
            break;
        case "unmark":
            parsedInput.add(retrieveIndex("unmark", inputSplit));
            break;
        case "todo":
            parsedInput = parseTask(input);
            break;
        case "event":
            parsedInput = parseEvent(inputSplit);
            break;
        case "deadline":
            parsedInput = parseDeadline(inputSplit);
            break;
        case "delete":
            parsedInput.add(retrieveIndex("delete", inputSplit));
            break;
        case "find":
            parsedInput.add(retrieveSearchTerm(input));
            break;
        default: // Do nothing
        }

        return parsedInput;
    }
}
