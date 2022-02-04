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
     * Returns the integer value after a single word.
     *
     * @param input The input string containing the number and a word.
     * @return The integer value after the word.
     */
    public static int getIndex(String input) {
        try {
            input = input.trim();
            String separator = " ";
            int pos = input.indexOf(separator);
            int res = Integer.parseInt(input.substring(pos + 1).trim());
            return res;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Returns the appropriate action to take after parsing a text
     * input.
     *
     * @param text The user's input text.
     * @return A Command object that will carry out the appropriate
     * action.
     */
    public Command parse(String text) {
        String input = text.trim();
        String command = getCommand(input);
        Command commandToExecute;
        switch(command) {
        case "list":
            commandToExecute = new ListCommand();
            break;
        case "mark":
        case "unmark":
        case "delete":
            commandToExecute = new NumberedCommand(getIndex(input), command, input);
            break;
        case "todo":
        case "deadline":
        case "event":
            commandToExecute = new AddCommand(command, input);
            break;
        case "find":
            commandToExecute = new FindCommand(input);
            break;
        case "clear":
            commandToExecute = new ClearCommand();
            break;
        case "bye":
            commandToExecute = new ExitCommand();
            break;
        default:
            commandToExecute = new InvalidCommand();
            break;
        }
        return commandToExecute;
    }

}
