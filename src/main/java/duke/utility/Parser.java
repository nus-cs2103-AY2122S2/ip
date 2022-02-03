package duke.utility;

import duke.command.*;
import duke.exception.DukeException;

import java.util.StringTokenizer;

/**
 * Parser for user input
 */
public class Parser {
    Parser(){
    }

    public static Command parse(String inputCommand) throws DukeException {
        StringTokenizer commandTokenizer = new StringTokenizer(inputCommand);
        Command command = null;

        switch(commandTokenizer.nextToken()){
            case "list":
                command = new ListCommand(inputCommand);
                break;
            case "todo":
            case "event":
            case "deadline":
                command = new AddCommand(inputCommand);
                break;
            case "mark":
            case "unmark":
                command = new MarkCommand(inputCommand);
                break;
            case "delete":
                command = new DeleteCommand(inputCommand);
                break;
            case "bye":
                command = new ExitCommand(inputCommand);
                break;
            case "find":
                command = new FindCommand(inputCommand);
        }
        if (command != null) {
            return command;
        } else {
            throw new DukeException("That is not a valid duke.command");
        }

    }
}
