package paggro.parser;

import paggro.command.ByeCommand;
import paggro.command.Command;
import paggro.command.DeadlineCommand;
import paggro.command.DeleteCommand;
import paggro.command.EventCommand;
import paggro.command.FindCommand;
import paggro.command.ListCommand;
import paggro.command.ListOnDateCommand;
import paggro.command.ListTagCommand;
import paggro.command.MarkCommand;
import paggro.command.TagCommand;
import paggro.command.ToDoCommand;
import paggro.command.UnmarkCommand;
import paggro.exception.PaggroException;

/**
 * This class encapsulates a Parser object which parses text input into a command.
 */
public class Parser {
    private static final String FOUR_SPACE = "    ";
    /**
     * Parses the String of user input into a command.
     *
     * @param input String given by user.
     * @return Command object associated with the input.
     * @throws PaggroException
     */
    public static Command parse(String input) throws PaggroException {
        String[] inputArr = input.split(" ", 2);
        String command = inputArr[0];
        if (command.equals("list")) {
            return new ListCommand();
        } else if (command.equals("mark")) {
            try {
                String parameters = inputArr[1];
                return new MarkCommand(parameters);
            } catch (ArrayIndexOutOfBoundsException e) { // no parameter given
                final String missingArgumentError = "Really? mark has to be used with a number... =.=";
                throw new PaggroException(FOUR_SPACE + missingArgumentError);
            }
        } else if (command.equals("unmark")) {
            try {
                String parameters = inputArr[1];
                return new UnmarkCommand(parameters);
            } catch (ArrayIndexOutOfBoundsException e) { // no parameter given
                final String missingArgumentError = "Really? unmark has to be used with a number... =.=";
                throw new PaggroException(FOUR_SPACE + missingArgumentError);
            }
        } else if (command.equals("todo")) {
            try {
                String parameters = inputArr[1];
                return new ToDoCommand(parameters);
            } catch (ArrayIndexOutOfBoundsException e) { // no description given
                final String missingDescriptionError = "Really? The description of a todo cannot be empty... =.=";
                throw new PaggroException(FOUR_SPACE + missingDescriptionError);
            }
        } else if (command.equals("deadline")) {
            try {
                String parameters = inputArr[1];
                return new DeadlineCommand(parameters);
            } catch (ArrayIndexOutOfBoundsException e) { // no description given
                final String missingDescriptionError = "Really? The description of a deadline cannot be empty... =.=";
                throw new PaggroException(FOUR_SPACE + missingDescriptionError);
            }
        } else if (command.equals("event")) {
            try {
                String parameters = inputArr[1];
                return new EventCommand(parameters);
            } catch (ArrayIndexOutOfBoundsException e) { // no description given
                final String missingDescriptionError = "Really? The description of an event cannot be empty... =.=";
                throw new PaggroException(FOUR_SPACE + missingDescriptionError);
            }
        } else if (command.equals("delete")) {
            try {
                String parameters = inputArr[1];
                return new DeleteCommand(parameters);
            } catch (ArrayIndexOutOfBoundsException e) { // no parameter given
                final String missingArgumentError = "Really? delete has to be used with a number... =.=";
                throw new PaggroException(FOUR_SPACE + missingArgumentError);
            }
        } else if (command.equals("find")) {
            try {
                String parameters = inputArr[1];
                return new FindCommand(parameters);
            } catch (ArrayIndexOutOfBoundsException e) { // no description given
                final String missingDescriptionError = "Really? The description of a find cannot be empty... =.=";
                throw new PaggroException(FOUR_SPACE + missingDescriptionError);
            }
        } else if (command.equals("listOnDate")) {
            try {
                String dateString = inputArr[1];
                return new ListOnDateCommand(dateString);
            } catch (ArrayIndexOutOfBoundsException e) { // no description given
                final String missingDateError = "Really? The date of a listOnDate cannot be empty... =.=";
                throw new PaggroException(FOUR_SPACE + missingDateError);
            }
        } else if (command.equals("listTag")) {
            try {
                String tagStr = inputArr[1];
                return new ListTagCommand((tagStr));
            } catch (ArrayIndexOutOfBoundsException e) {
                final String missingTagError = "Really? The tag of a listTag cannot be empty... =.=";
                throw new PaggroException(FOUR_SPACE + missingTagError);
            }
        } else if (command.equals("tag")) {
            try {
                String parameters = inputArr[1];
                return new TagCommand(parameters);
            } catch (ArrayIndexOutOfBoundsException e) {
                final String missingDescriptionError = "Really? The description of a tag cannot be empty... =.=";
                throw new PaggroException(FOUR_SPACE + missingDescriptionError);
            }
        } else if (command.equals("bye")) {
            return new ByeCommand();
        } else { // command not recognised
            final String invalidCommandError =
                    "Come on... You don't actually expect me to understand that right... =.=";
            throw new PaggroException(FOUR_SPACE + invalidCommandError);
        }
    }
}
