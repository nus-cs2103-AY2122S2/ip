package duke.parser;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TodoCommand;
import duke.command.UnmarkCommand;

import duke.dukeexceptions.DukeException;
import duke.dukeexceptions.ForeignException;
import duke.responses.Response;
import duke.responses.StartResponse;
import duke.responses.WelcomeResponse;

/***
 * Parser that deals with user inputs.
 */
public class Parser {
    /***
     * @return a WelcomeResponse.
     */
    public Response welcome() {
        return new WelcomeResponse();
    }
    /**
     * @return a StartResponse.
     */
    public Response start() {
        return new StartResponse();
    }

    /***
     * Takes in the string and returns the command
     * @param stringCmd this is the users input.
     * @return the appropriate Response based on the stringCmd.
     * @throws DukeException Error in the event of a unknown input.
     */
    public Command getCommand(String stringCmd) throws DukeException {
        String[] stringCmdUnits = stringCmd.split(" ");
        String stringFirstCmd = stringCmdUnits[0];
        switch (stringFirstCmd) {
        case "bye":
            return new ByeCommand();
        case "delete":
            return new DeleteCommand(stringCmd);
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(stringCmd);
        case "unmark":
            return new UnmarkCommand(stringCmd);
        case "todo":
            return new TodoCommand(stringCmd);
        case "deadline":
            return new DeadlineCommand(stringCmd);
        case "event":
            return new EventCommand(stringCmd);
        case "find":
            return new FindCommand(stringCmd);
        default:
            throw new ForeignException("");
        }
    }
}
