package paggro.parser;

import paggro.command.ByeCommand;
import paggro.command.Command;
import paggro.command.DeadlineCommand;
import paggro.command.DeleteCommand;
import paggro.command.EventCommand;
import paggro.command.ListCommand;
import paggro.command.ListOnDateCommand;
import paggro.command.MarkCommand;
import paggro.command.ToDoCommand;
import paggro.command.UnmarkCommand;
import paggro.exception.PaggroException;

/**
 * This class encapsulates a Parser object which parses text input into a command.
 */
public class Parser {
    /**
     * Parses the String of user input into a command.
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
                throw new PaggroException("    Really? mark has to be used with a number... =.=");
            }
        } else if (command.equals("unmark")) {
            try {
                String parameters = inputArr[1];
                return new UnmarkCommand(parameters);
            } catch (ArrayIndexOutOfBoundsException e) { // no parameter given
                throw new PaggroException("    Really? unmark has to be used with a number... =.=");
            }
        } else if (command.equals("todo")) {
            try {
                String parameters = inputArr[1];
                return new ToDoCommand(parameters);
            } catch (ArrayIndexOutOfBoundsException e) { // no description given
                throw new PaggroException("    Really? The description of a todo cannot be empty... =.=");
            }
        } else if (command.equals("deadline")) {
            try {
                String parameters = inputArr[1];
                return new DeadlineCommand(parameters);
            } catch (ArrayIndexOutOfBoundsException e) { // no description given
                throw new PaggroException("    Really? The description of a deadline cannot be empty... =.=");
            }
        } else if (command.equals("event")) {
            try {
                String parameters = inputArr[1];
                return new EventCommand(parameters);
            } catch (ArrayIndexOutOfBoundsException e) { // no description given
                throw new PaggroException("    Really? The description of an event cannot be empty... =.=");
            }
        } else if (command.equals("delete")) {
            try {
                String parameters = inputArr[1];
                return new DeleteCommand(parameters);
            } catch (NumberFormatException e) { // parameter was not a number
                throw new PaggroException("    Really? Can you input an actual number this time... =.=");
            } catch (ArrayIndexOutOfBoundsException e) { // no parameter given
                throw new PaggroException("    Really? delete has to be used with a number... =.=");
            }
        } else if (command.equals("listOnDate")) {
            try {
                String dateString = inputArr[1];
                return new ListOnDateCommand(dateString);
            } catch (ArrayIndexOutOfBoundsException e) { // no description given
                throw new PaggroException("    Really? The date of a listOnDate cannot be empty... =.=");
            }
        } else if (command.equals("bye")) {
            return new ByeCommand();
        } else { // command not recognised
            throw new PaggroException("    Come on... You don't actually expect me to understand that right... =.=");
        }
    }
}
