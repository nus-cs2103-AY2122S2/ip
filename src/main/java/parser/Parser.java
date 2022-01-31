package parser;

import commands.AddCommand;
import commands.ByeCommand;
import commands.Command;
import commands.DeleteCommand;
import commands.FindCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.UnMarkCommand;
import exceptions.DukeException;
import exceptions.EmptyDescriptionException;
import exceptions.IncorrectValueException;
import exceptions.UnknownCommandException;

/**
 * class which handles processing of user input and output as correct command
 */
public class Parser {

    public Parser(){}

    /**
     * Method to process the users input into respective command
     * @param input Users input to be processed
     * @return Corresponding command if valid input
     * @throws DukeException if input is invalid or missing details
     */
    public static Command processInput(String input) throws DukeException {
        Command cmd = null;
        String[] inputSplit = input.split(" ", 2);
        String commandType = inputSplit[0].toLowerCase();
        switch (commandType) {
        case "bye":
            cmd = new ByeCommand();
            break;
        case "list":
            cmd = new ListCommand();
            break;
        case "mark":
        case "unmark":
        case "delete":
            String detail = inputSplit[1];
            if (!isInteger(detail)) {
                throw new IncorrectValueException();
            }
            int inputVal = Integer.parseInt(detail);
            switch (commandType) {
            case "mark":
                cmd = new MarkCommand(inputVal);
                break;
            case "unmark":
                cmd = new UnMarkCommand(inputVal);
                break;
            case "delete":
                cmd = new DeleteCommand(inputVal);
                break;
            default:
                break;
            }
            break;
        case "find":
        case "todo":
        case "event":
        case "deadline":
            if (inputSplit.length < 2) {
                throw new EmptyDescriptionException(commandType);
            }
            switch (commandType) {
            case "find":
                cmd = new FindCommand(inputSplit[1]);
                break;
            default:
                cmd = new AddCommand(commandType, inputSplit[1]);
                break;
            }
            break;
        default:
            throw new UnknownCommandException();
        }
        return cmd;
    }

    /**
     * Method to check input string is a valid string of integers
     * @param input String input to be checked
     * @return true if input is all integers false is not
     */
    private static boolean isInteger(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
