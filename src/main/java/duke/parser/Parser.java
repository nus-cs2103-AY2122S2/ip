package main.java.duke.parser;

import main.java.duke.command.*;
import main.java.duke.dukeexceptions.DukeException;
import main.java.duke.dukeexceptions.ForeignException;
import main.java.duke.responses.Response;
import main.java.duke.responses.StartResponse;
import main.java.duke.responses.WelcomeResponse;

import java.util.ArrayList;
import java.util.Arrays;

/***
 * Parser that deals with user inputs.
 */

public class Parser {
    static private ArrayList<String> commandList = new ArrayList<>(
            Arrays.asList("bye", "delete", "list", "mark", "unmark", "todo", "deadline", "event"));

    /**
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
     * 
     * @param stringCmd this is the users input.
     * @return the appropriate Response based on the stringCmd.
     * @throws DukeException Error in the event of a unknown input.
     */
    
    public Command getCommand(String stringCmd) throws DukeException {
        
        String[] cmdArr = stringCmd.split(" ");
        String stringFirstCmd = cmdArr[0];
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
            return  new EventCommand(stringCmd);
        default:
            throw new ForeignException("");
        }
    }
}
