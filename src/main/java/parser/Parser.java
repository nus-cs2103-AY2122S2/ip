package parser;

import command.*;
import exception.DukeException;

/**
 * Enum to distinguish commands.
 */
public class Parser {
    public enum Commands {
        TODO,
        DEADLINE,
        EVENT,
        LIST,
        MARK,
        UNMARK,
        DELETE,
        BYE;
    }

    /**
     * Processes a raw String command and decides the command.
     * If an error is detected, Duke exception is thrown.
     *
     * @param fullCommand Raw String command
     * @return Command to be executed
     * @throws DukeException If an error occurs when processing a command
     */
    public static Command parse(String fullCommand) throws DukeException {
        String response = fullCommand.trim();
        if (response.equals("")) {
            return new IncorrectCommand();
        }
        String[] responseArray = response.split("\\s+"), secondSplit;
        Commands command;
        if (responseArray.length > 0) {
            try {
                command = Commands.valueOf(responseArray[0].toUpperCase());
                switch (command) {
                    case TODO:
                        return new TodoCommand(removeSubString(response, "todo "));
                    case DEADLINE:
                        try {
                            secondSplit = removeSubString(response, "deadline ").split(" /by ");
                            return new DeadlineCommand(secondSplit[0], secondSplit[1]);
                        } catch (IndexOutOfBoundsException e) {
                            throw new DukeException("date or time was not specified! Try again.");
                        }
                    case EVENT:
                        try {
                            secondSplit = removeSubString(response, "event ").split(" /at ");
                            return new EventCommand(secondSplit[0], secondSplit[1]);
                        } catch (IndexOutOfBoundsException e) {
                            throw new DukeException("location was not specified! Try again.");
                        }
                    case MARK:
                        return new MarkCommand(Integer.parseInt(responseArray[1]));
                    case UNMARK:
                        return new UnmarkCommand(Integer.parseInt(responseArray[1]));
                    case DELETE:
                        return new DeleteCommand(Integer.parseInt(responseArray[1]));
                    case BYE:
                        return new ExitCommand();
                    case LIST:
                        return new ListCommand();
                    default:
                        break;
                }
            } catch (DukeException e) {
                return new IncorrectCommand(e.getMessage());
            } catch (IllegalArgumentException e) {
                return new IncorrectCommand();
            }
        }
        return new IncorrectCommand();
    }

    /**
     * Removes a word from a String of words.
     *
     * @param response String of text to remove a word from
     * @param word Word to be removed
     * @return A substring that removes a specific word from a string of text
     */
    public static String removeSubString(String response, String word) {
        return response.replace(word, "");
    }
}
