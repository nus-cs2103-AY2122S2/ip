package duke.admin;

import duke.commands.AddCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.UnmarkCommand;
import duke.exceptions.DukeException;

/**
 * Parser class parses the command passed in as a String and represents it as a
 * Command that the program can manage.
 */
public class Parser {
    private static String description;

    /**
     * Returns the relevent Command object to be executed from the string input.
     * @param fullCommand the command as a String
     * @return Command object that triggers an action from the program based on the
     *         command
     * @throws DukeException exception thrown when command is invalid or improper
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] splitCommand = fullCommand.split(" ", 2); //splits command into action and details
        String action = splitCommand[0];
        boolean descriptionExist = splitCommand.length > 1;

        if (descriptionExist) {
            description = splitCommand[1];
        }

        switch (action) {
        case "list":
            if (descriptionExist) {
                throw new DukeException("There should not be anything else after list.");
            } else {
                return new ListCommand();
            }
        case "mark":
            if (!(descriptionExist)) {
                throw new DukeException("I don't know what to mark!! :-(");
            } else {
                int index = Integer.parseInt(description) - 1;
                return new MarkCommand(index);
            }
        case "unmark":
            if (!(descriptionExist)) {
                throw new DukeException("I don't know what to unmark!! :-(");
            } else {
                int index = Integer.parseInt(description) - 1;
                return new UnmarkCommand(index);
            }
        case "todo":
            if (!(descriptionExist)) {
                throw new DukeException("The description of a todo cannot be empty.");
            } else {
                return new AddCommand("T", description);
            }
        case "deadline":
            if (!(descriptionExist)) {
                throw new DukeException("The description of a deadline cannot be empty.");
            } else {
                return new AddCommand("D", description);
            }
        case "event":
            if (!(descriptionExist)) {
                throw new DukeException("The description of an event cannot be empty.");
            } else {
                return new AddCommand("E", description);
            }
        case "delete":
            if (!(descriptionExist)) {
                throw new DukeException("I don't know what to delete!! :-(");
            } else {
                int index = Integer.parseInt(description) - 1;
                return new DeleteCommand(index);
            }
        case "bye":
            return new ExitCommand();
        case "find":
            return new FindCommand(description);
        default:
            throw new DukeException("Invalid command :-(");
        }
    }
}
