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
        String[] splitCommand = fullCommand.split(" ", 2); //splits command into action and details
        String action = splitCommand[0];
        boolean descriptionExists = splitCommand.length > 1;

        if (descriptionExists) {
            description = splitCommand[1];
        }

        switch (action) {
        case "list": //List tasks function
            return new ListCommand();
        case "mark": //Mark tasks function
            if (!(descriptionExists)) {
                throw new DukeException("I don't know what to mark!! :-(");
            } else {
                int index = Integer.parseInt(description) - 1;
                return new ChangeMarkCommand(index, true);
            }
        case "unmark": //Unmark tasks function
            if (!(descriptionExists)) {
                throw new DukeException("I don't know what to unmark!! :-(");
            } else {
                int index = Integer.parseInt(description) - 1;
                return new ChangeMarkCommand(index, false);
            }
        case "todo": //Add To Do task function
            if (!(descriptionExists)) {
                throw new DukeException("The description of a todo cannot be empty.");
            } else {
                return new AddCommand("T", description);
            }
        case "deadline": //Add Deadline task function
            if (!(descriptionExists)) {
                throw new DukeException("The description of a deadline cannot be empty.");
            } else {
                return new AddCommand("D", description);
            }
        case "event": //Add Event task function
            if (!(descriptionExists)) {
                throw new DukeException("The description of an event cannot be empty.");
            } else {
                return new AddCommand("E", description);
            }
        case "delete": //Deletes task function
            if (!(descriptionExists)) {
                throw new DukeException("I don't know what to delete!! :-(");
            } else {
                int index = Integer.parseInt(description) - 1;
                return new DeleteCommand(index);
            }
        case "bye": //Exit function
            return new ExitCommand();
        case "find": //Find task function
            if (!(descriptionExists)) {
                throw new DukeException("The keyword to search for cannot be empty.");
            } else {
                return new FindCommand(description);
            }
        case "update": //Update task function
            if (!(descriptionExists)) {
                throw new DukeException("Improper format to request for an update.");
            } else {
                return new UpdateCommand(description);
            }
        case "clone": //Clone task function
            if (!(descriptionExists)) {
                throw new DukeException("I don't know which task to clone!");
            } else {
                int index = Integer.parseInt(description) - 1;
                return new CloneCommand(index);
            }
        default:
            throw new DukeException("Invalid command :-(");
        }
    }
}
