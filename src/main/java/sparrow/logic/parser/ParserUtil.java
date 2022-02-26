package sparrow.logic.parser;

import java.util.Arrays;

import sparrow.commons.exceptions.ParseException;
import sparrow.model.Priority;

public class ParserUtil {
    private static final String INDEX_NON_POSITIVE_MESSAGE = "Index must be greater than 0.";
    private static final String INDEX_NON_INTEGER_MESSAGE = "Index must be an integer.";
    /**
     * Parses the command from the user input.
     */
    public static String parseCommand(String userInput) {
        return userInput.split(" ")[0];
    }
    /**
     * Parses the command arguments from the user input.
     */
    public static String parseArgs(String userInput) {
        String[] userInputArr = userInput.split(" ");
        return String.join(" ", Arrays.copyOfRange(userInputArr, 1, userInputArr.length));
    }

    /**
     * Parses a one-based {@code String} index into a zero-based {@code int} index.
     *
     * @throws ParseException if the index is invalid.
     */
    public static int parseIndex(String index) throws ParseException {
        try {
            int value = Integer.parseInt(index) - 1;
            if (value < 0) {
                throw new ParseException(INDEX_NON_POSITIVE_MESSAGE);
            }
            return value;
        } catch (NumberFormatException e) {
            throw new ParseException(INDEX_NON_INTEGER_MESSAGE);
        }
    }

    /**
     * Parses the input into a task priority.
     *
     * @param s the input.
     * @return A task priority.
     */
    public static Priority parsePriority(String s) {
        switch (s) {
        case "low":
            return Priority.LOW;
        case "high":
            return Priority.HIGH;
        default:
            return Priority.MEDIUM;
        }
    }
}
