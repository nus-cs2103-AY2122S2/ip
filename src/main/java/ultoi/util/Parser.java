package ultoi.util;

import ultoi.task.Event;
import ultoi.task.Deadline;

import ultoi.command.Command;
import ultoi.command.AddCommand;
import ultoi.command.ByeCommand;
import ultoi.command.DeleteCommand;
import ultoi.command.FindCommand;
import ultoi.command.ListCommand;
import ultoi.command.MarkCommand;

/**
 * Represents a parser used to convert user input into valid commands.
 *
 * @author snoidetx
 * @version 0.0.0
 */
public class Parser {
    /**
     * Returns a ultoi.command.Command object that input represents.
     *
     * @param input User input.
     * @return ultoi.command.Command object that input represents.
     * @throws UltoiException If input is not identifiable.
     */
    public static Command parse(String input) throws UltoiException {
        String[] tokens = input.split(" ");

        try {
            switch (tokens[0]) {
            case "list":
                return new ListCommand(input);
            case "todo":
            case "deadline":
            case "event":
                return new AddCommand(input);
            case "delete":
                return new DeleteCommand(input);
            case "mark":
            case "unmark":
                return new MarkCommand(input);
            case "find":
                return new FindCommand(input);
            case "bye":
                return new ByeCommand(input);
            default:
                throw UltoiException.noSuchCommandException();
            }
        } catch (UltoiException e) {
            throw e;
        }
    }

    /**
     * Checks if the input is a bye command.
     *
     * @param input User input.
     * @return True if the input is "bye".
     */
    public static boolean isBye(String input) {
        return input.equals("bye");
    }

    /**
     * Returns a Deadline object parsed from user input.
     * This method will be depreciated in future.
     *
     * @param input User input.
     * @return Deadline object represented by the input.
     * @throws UltoiException If user input cannot be parsed.
     */
    public static Deadline parseDeadline(String input) throws UltoiException {
        String description = "";
        String time = "";

        String[] tokens = input.split(" ");

        for (int i = 1; i < tokens.length; i++) {
            if (tokens[i].equals(AddCommand.COMMAND_BY)) {
                for (int j = i + 1; j < tokens.length; j++) {
                    time += tokens[j] + " ";
                }
                break;
            } else {
                description += tokens[i] + " ";
            }
        }

        description = description.substring(0, description.length() - 1);
        time = time.substring(0, time.length() - 1);

        return new Deadline(description, time);
    }

    /**
     * Returns a Event object parsed from user input.
     * This method will be depreciated in future.
     *
     * @param input User input.
     * @return Event object represented by the input.
     * @throws UltoiException If user input cannot be parsed.
     */
    public static Event parseEvent(String input) throws UltoiException {
        String description = "";
        String time = "";

        String[] tokens = input.split(" ");
        
        for (int i = 1; i < tokens.length; i++) {
            if (tokens[i].equals(AddCommand.COMMAND_AT)) {
                for (int j = i + 1; j < tokens.length; j++) {
                    time += tokens[j] + " ";
                }
                break;
            } else {
                description += tokens[i] + " ";
            }
        }

        description = description.substring(0, description.length() - 1);
        time = time.substring(0, time.length() - 1);

        return new Event(description, time);
    }
}
