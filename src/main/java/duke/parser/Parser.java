package duke.parser;

import duke.command.*;

/**
 * Represents a parser object that formats and retrieves
 * information from users' text inputs.
 */
public class Parser {

    /**
     * Returns the first word of a string.
     *
     * @param input The given input string.
     * @return The first word of the input.
     */
    public static String getCommand(String input) {
        int index = input.indexOf(' ');
        return (index >= 0) ? input.substring(0, index) : input;
    }

    /**
     * Obtains the integer value after a single word.
     *
     * @param input The input string containing the number and a word.
     * @return The integer value after the word.
     */
    public static int getIndex(String input) {
        try {
            input = input.trim();
            String seperator = " ";
            int pos = input.indexOf(seperator);
            int res = Integer.parseInt(input.substring(pos + 1).trim());
            return res;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Parses a text input and returns the appropriate action to
     * take.
     *
     * @param text The user's input text.
     * @return A Command object that will carry out the appropriate
     * action.
     */
    public Command parse(String text) {
        String input = text.trim();
        String command = getCommand(input);
        if (command.equals("list")) {
            return new ListCommand();
        } else if (command.equals("mark") || command.equals("unmark")
                || command.equals("delete")) {
            return new NumberedCommand(getIndex(input), command, input);
        } else if (command.equals("bye")) {
            return new ExitCommand();
        } else if (command.equals("todo") || command.equals("deadline")
                || command.equals("event")) {
            return new AddCommand(command, input);
        } else if (command.equals("find")) {
            return new FindCommand(input);
        } else if (command.equals("clear")) {
            return new ClearCommand();
        } else {
            return new InvalidCommand();
        }
    }

}
