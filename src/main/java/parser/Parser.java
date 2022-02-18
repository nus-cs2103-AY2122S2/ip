package parser;

import java.util.ArrayList;

import command.Command;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.EventCommand;
import command.ExitCommand;
import command.FindCommand;
import command.HelpCommand;
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
        HELP,
        FIND;
    }

    public static final Priorities DEFAULT_PRIORITY = Priorities.NORMAL;

    /**
     * Processes a raw String command and decides the command.
     * If an error is detected, duke.Duke exception is thrown.
     *
     * @param fullCommand Raw String command
     * @return Command to be executed
     * @throws DukeException If an error occurs when processing a command
     */
    public static Command parse(String fullCommand) throws DukeException {
        Commands command = null;
        String response = fullCommand.trim();
        String[] responseArray;
        String[] secondSplit;
        Priorities priority = DEFAULT_PRIORITY;
        if (response.equals("")) {
            return new IncorrectCommand();
        }
        responseArray = response.split("\\s+");
        if (responseArray.length > 0) {
            try {
                command = Commands.valueOf(responseArray[0].toUpperCase());
                String textContent = removeSubString(response, responseArray[0] + " ");
                // if a command is creating a task, get the priority
                if (responseArray.length > 1 && containsPriority(responseArray[1])) {
                    priority = getPriority(responseArray[1]);
                    // remove the priority number from the content of the task message
                    textContent = removeSubString(textContent, responseArray[1]);
                }
                switch (command) {
                case FIND:
                    return new FindCommand(textContent);
                case TODO:
                    return new TodoCommand(textContent, priority);
                case DEADLINE:
                    secondSplit = textContent.split(" /by ");
                    return new DeadlineCommand(secondSplit[0], secondSplit[1], priority);
                case EVENT:
                    secondSplit = textContent.split(" /at ");
                    return new EventCommand(secondSplit[0], secondSplit[1], priority);
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
                case HELP:
                    return new HelpCommand();
                default:
                    break;
                }
                assert command != null;
            } catch (DukeException e) {
                return new IncorrectCommand(e.getMessage());
            } catch (IllegalArgumentException e) {
                return new IncorrectCommand();
            } catch (IndexOutOfBoundsException e) {
                switch (command) {
                case DEADLINE:
                    throw new DukeException("date or time was not specified! Try again.");
                case EVENT:
                    throw new DukeException("location was not specified! Try again.");
                default:
                    break;
                }
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

    /**
     * Check if a string contains a priority attribute.
     *
     * @param strNum String to compare with
     * @return Boolean -> false = does not contain
     */
    public static boolean containsPriority(String strNum) {
        if (strNum == null || strNum.equals("")) {
            return false;
        } else {
            try {
                Priorities priority = Priorities.valueOf(strNum.toUpperCase());
            } catch (IllegalArgumentException nfe) {
                return false;
            }
        }
        return true;
    }

    /**
     * Get the priority enum.
     *
     * @param strNum String to compare with
     * @return Integer -> -1 = not convertible. Otherwise, the value
     */
    public static Priorities getPriority(String strNum) {
        Priorities priority = DEFAULT_PRIORITY;
        if (strNum == null || strNum.equals("")) {
            return priority;
        } else {
            try {
                priority = Priorities.valueOf(strNum.toUpperCase());
            } catch (IllegalArgumentException nfe) {
                return priority;
            }
        }
        return priority;
    }
}
