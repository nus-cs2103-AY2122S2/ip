package duke.parser;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.DukeWrongInputFormatException;

public class Parser {
    public static Command parseString(String[] command) throws DukeException {
        switch (command[0].toLowerCase()) {
        case "":
            return Command.EMPTY;
        case "bye":
            if (command.length > 1 && !command[1].equals(""))
                throw new DukeWrongInputFormatException("Command bye should not have any arguments");
            return Command.BYE;
        case "list":
            if (command.length > 1 && !command[1].equals(""))
                throw new DukeWrongInputFormatException("Command list should not have any arguments");
            return Command.LIST;
        case "mark":
            if (command.length <= 1 || command[1].equals(""))
                throw new DukeWrongInputFormatException("Missing task number to mark");
            return Command.MARK;
        case "unmark":
            if (command.length <= 1 || command[1].equals(""))
                throw new DukeWrongInputFormatException("Missing task number to unmark");
            return Command.UNMARK;
        case "delete":
            if (command.length <= 1 || command[1].equals(""))
                throw new DukeWrongInputFormatException("Missing task number to delete");
            return Command.DELETE;
        case "todo":
            if (command.length <= 1 || command[1].equals(""))
                throw new DukeWrongInputFormatException("The description of a todo cannot be empty.");
            return Command.TODO;
        case "deadline":
            if (command.length <= 1 || command[1].equals(""))
                throw new DukeWrongInputFormatException("The description of a deadline cannot be empty.");
            return Command.DEADLINE;
        case "event":
            if (command.length <= 1 || command[1].equals(""))
                throw new DukeWrongInputFormatException("The description of an event cannot be empty.");
            return Command.EVENT;
        case "find":
            if (command.length <= 1 || command[1].equals("")) {
                throw new DukeWrongInputFormatException("Missing keyword to find");
            }
            return Command.FIND;
        default:
            return Command.INVALID;
        }
    }
}
