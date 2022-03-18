package duke.admin;

import duke.commands.AddCommand;
import duke.commands.ChangeMarkCommand;
import duke.commands.CloneCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.UpdateCommand;
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
        String action = fullCommand.split(" ", 2)[0];

        if (fullCommand.split(" ", 2).length > 1) {
            description = fullCommand.split(" ", 2)[1];
        }

        try {
            switch (action) {
            case "list": //List tasks function
                return new ListCommand();
            case "mark": //Mark tasks function
                return new ChangeMarkCommand(description, true);
            case "unmark": //Unmark tasks function
                return new ChangeMarkCommand(description, false);
            case "todo": //Add To Do task function
                return new AddCommand("T", description);
            case "deadline": //Add Deadline task function
                return new AddCommand("D", description);
            case "event": //Add Event task function
                return new AddCommand("E", description);
            case "delete": //Deletes task function
                return new DeleteCommand(description);
            case "bye": //Exit function
                return new ExitCommand();
            case "find": //Find task function
                return new FindCommand(description);
            case "update": //Update task function
                return new UpdateCommand(description);
            case "clone": //Clone task function
                return new CloneCommand(description);
            default:
                throw new DukeException(DukeException.INVALID_COMMAND);
            }
        } catch (NullPointerException ne) {
            throw new DukeException(DukeException.INVALID_FORMAT);
        }
    }
}
