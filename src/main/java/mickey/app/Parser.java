package mickey.app;

import mickey.command.AddCommand;
import mickey.command.ByeCommand;
import mickey.command.Command;
import mickey.command.DeleteCommand;
import mickey.command.FindCommand;
import mickey.command.ListCommand;
import mickey.command.MarkCommand;

/**
 * Parser to handle input commands to Mickey.
 */
public class Parser {

    /**
     * Constructor.
     */
    public Parser() {
    }

    /**
     * Returns command based on the command parsed.
     *
     * @param fullCommand User input command.
     * @return Corresponding command object called by user.
     * @throws MickeyException Exception for invalid commands.
     */
    public static Command parse(String fullCommand) throws MickeyException {
        String[] args = fullCommand.split(" ");
        switch (args[0]) {
            case "todo":
            case "deadline":
            case "event":
                return new AddCommand(fullCommand);
            case "list":
                return new ListCommand(fullCommand);
            case "mark":
            case "unmark":
                return new MarkCommand(fullCommand);
            case "delete":
                return new DeleteCommand(fullCommand);
            case "bye":
                return new ByeCommand(fullCommand);
            case "find":
                return new FindCommand(fullCommand);
            default:
                throw new MickeyException("\tOh no! This is a disaster! I don't know what that means");
        }
    }
}
