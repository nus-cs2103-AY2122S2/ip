package parser;

import java.util.ArrayList;

import command.Command;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.EventCommand;
import command.ExitCommand;
import command.FindCommand;
import command.IncorrectCommand;
import command.ListCommand;
import command.MarkCommand;
import command.TodoCommand;
import command.UnmarkCommand;
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
        BYE,
        FIND;
    }

    /**
     * Processes a raw String command and decides the command.
     * If an error is detected, duke.Duke exception is thrown.
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
        String[] responseArray;
        responseArray = response.split("\\s+");
        String[] secondSplit;
        Commands command;
        if (responseArray.length > 0) {
            try {
                command = Commands.valueOf(responseArray[0].toUpperCase());
                String textContent = removeSubString(response.toLowerCase(), responseArray[0].toLowerCase() + " ");
                switch (command) {
                case FIND:
                    return new FindCommand(textContent);
                case TODO:
                    return new TodoCommand(textContent);
                case DEADLINE:
                    try {
                        secondSplit = textContent.split(" /by ");
                        return new DeadlineCommand(secondSplit[0], secondSplit[1]);
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("date or time was not specified! Try again.");
                    }
                case EVENT:
                    try {
                        secondSplit = textContent.split(" /at ");
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
                assert command != null;
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

    /**
     * Converts an ArrayList of content into a single String.
     *
     * @param l ArrayList to convert from
     * @return Single string the represents the content of the ArrayList
     */
    public static String arrayListToString(ArrayList<String> l) {
        StringBuilder sb = new StringBuilder();
        for (String con : l) {
            sb.append(con);
        }
        return sb.toString();
    }
}
