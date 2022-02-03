package src.main.java.duke;

import src.main.java.duke.command.AddCommand;
import src.main.java.duke.command.Command;
import src.main.java.duke.command.DeleteCommand;
import src.main.java.duke.command.ExitCommand;
import src.main.java.duke.command.FindCommand;
import src.main.java.duke.command.ListCommand;
import src.main.java.duke.command.MarkCommand;
import src.main.java.duke.command.UnmarkCommand;

public class Parser {
    private static String description;

    public static Command parse(String fullCommand) throws DukeException {
        String[] splitCommand = fullCommand.split(" ", 2);
        String action = splitCommand[0];

        if (splitCommand.length > 1) {
            description = splitCommand[1];
        }

        switch (action) {
            case "list":
                if (splitCommand.length > 1) {
                    throw new DukeException("There should not be anything else after list.");
                } else {
                    return new ListCommand();
                }
            case "mark":
                if (splitCommand.length < 2) {
                    throw new DukeException("I don't know what to mark!! :-(");
                } else {
                    int idx = Integer.parseInt(description) - 1;
                    return new MarkCommand(idx);
                }
            case "unmark":
                if (splitCommand.length < 2) {
                    throw new DukeException("I don't know what to unmark!! :-(");
                } else {
                    int idx = Integer.parseInt(description) - 1;
                    return new UnmarkCommand(idx);
                }
            case "todo":
                if (splitCommand.length < 2) {
                    throw new DukeException("The description of a todo cannot be empty.");
                } else {
                    return new AddCommand("T", description);
                }
            case "deadline":
                if (splitCommand.length < 2) {
                    throw new DukeException("The description of a deadline cannot be empty.");
                } else {
                    return new AddCommand("D", description);
                }
            case "event":
                if (splitCommand.length < 2) {
                    throw new DukeException("The description of an event cannot be empty.");
                } else {
                    return new AddCommand("E", description);
                }
            case "delete":
                if (splitCommand.length < 2) {
                    throw new DukeException("I don't know what to delete!! :-(");
                } else {
                    int idx = Integer.parseInt(description) - 1;
                    return new DeleteCommand(idx);
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
