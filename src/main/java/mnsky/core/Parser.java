package mnsky.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mnsky.exceptions.MnskyException;
import mnsky.exceptions.MnskyMissingParameterException;

public class Parser {
    /**
     * Retrieves and returns the value of the index parameter from the input.
     * @param command The name of the command that called this function.
     * @param inputSplit The input, split into an array using space.
     * @return The value of the index parameter.
     * @throws MnskyMissingParameterException If the index parameter is missing.
     */
    private static String retrieveIndex(String command, String[] inputSplit) throws MnskyMissingParameterException {
        if (inputSplit.length < 2) {
            throw new MnskyMissingParameterException(command, "index");
        }

        return inputSplit[1];
    }

    /**
     * Creates a new task by parsing the input.
     * @param input The input string.
     * @return The new task.
     * @throws MnskyMissingParameterException If the name parameter is missing.
     */
    private static ArrayList<String> parseTask(String input) throws MnskyMissingParameterException {
        String[] inputSplit = input.split(" ", 2);
        if (inputSplit.length < 2) {
            throw new MnskyMissingParameterException("todo", "name");
        }

        return new ArrayList<>(List.of("task", inputSplit[1]));
    }

    /**
     * Creates a new deadline (a task with a "by" parameter included) by parsing the input.
     * @param inputSplit The input, split into an array using space.
     * @return The new deadline.
     * @throws MnskyMissingParameterException Thrown if the name or the by parameter is missing.
     */
    private static ArrayList<String> parseDeadline(String[] inputSplit) throws MnskyMissingParameterException {
        if (inputSplit.length < 2) {
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

        String deadlineName = String.join(" ", Arrays.copyOfRange(inputSplit, 1, byIndex));
        String by = String.join(" ", Arrays.copyOfRange(inputSplit, byIndex + 1, inputSplit.length));

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

        String eventName = String.join(" ", Arrays.copyOfRange(inputSplit, 1, atIndex));
        String at = String.join(" ", Arrays.copyOfRange(inputSplit, atIndex + 1, inputSplit.length));

        return new ArrayList<>(List.of("event", eventName, at));
    }

    private static String retrieveSearchTerm(String input) {
        String[] inputSplit = input.split(" ", 2);
        if (inputSplit.length < 2) {
            throw new MnskyMissingParameterException("find", "search_term");
        }

        return inputSplit[1];
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

                if (line.charAt(1) == 'T') {
                    nextTask = parseTask(line);
                } else if (line.charAt(1) == 'D') {
                    nextTask = parseDeadline(lineSplit);
                } else if (line.charAt(1) == 'E') {
                    nextTask = parseEvent(lineSplit);
                }

                if (nextTask != null) {
                    if (nextTask.size() < 3) {
                        nextTask.add("");
                    }
                    nextTask.add(line.substring(4, 5));
                    tasks.add(nextTask);
                }
            }

            return tasks;
        } catch (MnskyMissingParameterException e) {
            throw new MnskyException("[MNSKY is having trouble remembering the previous task list...]\n");
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

        switch (inputSplit[0]) {
        case "bye":
            parsedInput.add("bye");
            break;
        case "list":
            parsedInput.add("list");
            break;
        case "mark":
            parsedInput.add("mark");
            parsedInput.add(retrieveIndex("mark", inputSplit));
            break;
        case "unmark":
            parsedInput.add("unmark");
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
            parsedInput.add("delete");
            parsedInput.add(retrieveIndex("delete", inputSplit));
            break;
        case "find":
            parsedInput.add("find");
            parsedInput.add(retrieveSearchTerm(input));
            break;
        default:
            parsedInput.add("invalid");
        }

        return parsedInput;
    }
}
