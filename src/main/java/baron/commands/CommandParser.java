package baron.commands;

import baron.exceptions.BaronException;

/**
 * Assists in the parsing of commands.
 */
public class CommandParser {

    /**
     * Parses the given string into an integer for task index.
     *
     * @param arg the argument of {@code String} type.
     * @return the integer represented by the given string.
     * @throws BaronException if NumberFormatException is thrown by Integer.parseInt.
     * @see Integer#parseInt(String)
     */
    public static int parseTaskIntArg(String arg) throws BaronException {
        if (arg.isBlank()) {
            throw new BaronException("Please enter the index of the task!");
        }

        int intVal;
        try {
            intVal = Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            throw new BaronException("Invalid argument type, please give a valid integer!");
        }

        return intVal;
    }
}
