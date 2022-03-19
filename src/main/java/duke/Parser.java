package duke;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListTaskCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;

/**
 * Parses the user input and triggers commands to run the input information.
 */
public class Parser {
    private String userInput;
    private String[] textEntered;
    private String command;
    private Checker checker;
    private Storage storage;
    private Command parsedCommand;
    private static final int COMMAND_INDEX = 0;
    private static final int DETAILS_INDEX = 1;
    private static final int DESCRIPTION_INDEX = 0;
    private static final int TIME_INDEX = 1;

    private static String DESC_RESPONSE = "Oops! \\(@.@)/ You have not keyed in a description for the "
            + "task!\n"
            + "Let's try again ~(^.^)~\n"
            + "Type 'help' if you need to know how to use this command";

    private static String MISSING_ID_RESPONSE = "Oops! \\(@.@)/ You have not keyed in an ID!\n"
            + "Let's try again ~(^.^)~\n"
            + "Type 'help' if you need to know how to use this command";

    private static String MISSING_DATE_RESPONSE = "Oops, please specify a date!";

    private static final String INVALID_RESPONSE = "Sorry, I did not catch that! \\(T.T)/\n"
            + "type 'help' to see all commands I can help with.";

    /**
     * Constructor method for Parser.
     *
     * @param input User's input into CLI (e.g. event jumping /at 6 January 2023).
     */
    public Parser(String input) {
        userInput = input;
    }

    /**
     * Parses the user input to determine the commands to be used.
     *
     * @return Command that is to be called based on the user's input.
     * @throws DukeException Throws a DukeException if there is missing or incorrect information from the user input.
     */
    public Command parse() throws DukeException {
        textEntered = userInput.split(" ", 2);
        command = textEntered[COMMAND_INDEX];
        checker = new Checker(command);

        switch (checker.getStatus()) {
        case BYE:
            parsedCommand = new ExitCommand();
            break;
        case HELP:
            parsedCommand = new HelpCommand();
            break;
        case LIST:
            parsedCommand = new ListTaskCommand();
            break;
        case DELETE:
            try {
                String deleteId = textEntered[DETAILS_INDEX];
                parsedCommand = new DeleteCommand(deleteId);
            } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
                throw new DukeException(MISSING_ID_RESPONSE);
            }
            break;
        case TODO:
            try {
                String description = textEntered[DETAILS_INDEX];
                parsedCommand = new AddTodoCommand(description);
            } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
                throw new DukeException(DESC_RESPONSE);
            }
            break;
        case DEADLINE:
            try {
                String text = textEntered[DETAILS_INDEX];
                if (!text.contains("/by")) {
                    throw new DukeException("Please use \"/by\"");
                }
                String[] textArr = text.split("/by");
                String description = textArr[DESCRIPTION_INDEX].trim();
                if (textArr.length == 1) {
                    throw new DukeException(MISSING_DATE_RESPONSE);
                }
                String time = textArr[1].trim();
                parsedCommand = new AddDeadlineCommand(description, time);
            } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
                throw new DukeException(DESC_RESPONSE);
            }
            break;
        case EVENT:
            try {
                String text = textEntered[DETAILS_INDEX];
                if (!text.contains("/at")) {
                    throw new DukeException("Please use \"/at\"");
                }
                String[] textArr = text.split("/at ");
                String description = textArr[DESCRIPTION_INDEX].trim();
                if (textArr.length == 1) {
                    throw new DukeException(MISSING_DATE_RESPONSE);
                }
                String time = textArr[TIME_INDEX];
                parsedCommand = new AddEventCommand(description, time);
            } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
                throw new DukeException(DESC_RESPONSE);
            }
            break;
        case UNMARK:
            try {
                String unmarkId = textEntered[DETAILS_INDEX];
                parsedCommand = new UnmarkCommand(unmarkId);
            } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
                throw new DukeException(MISSING_ID_RESPONSE);
            }
            break;
        case MARK:
            try {
                String markId = textEntered[DETAILS_INDEX];
                parsedCommand = new MarkCommand(markId);
            } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
                throw new DukeException(MISSING_ID_RESPONSE);
            }
            break;
        case FIND:
            try {
                String findString = textEntered[DETAILS_INDEX];
                parsedCommand = new FindCommand(findString);
            } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
                throw new DukeException(MISSING_ID_RESPONSE);
            }
            break;
        default:
            throw new DukeException(INVALID_RESPONSE);
        }
        return parsedCommand;
    }
}
