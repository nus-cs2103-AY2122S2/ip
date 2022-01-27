package main.java.duke.parser;

import main.java.duke.command.*;
import main.java.duke.dukeexceptions.DukeException;
import main.java.duke.dukeexceptions.ForeignException;
import main.java.duke.responses.Response;
import main.java.duke.responses.StartResponse;
import main.java.duke.responses.WelcomeResponse;

public class Parser {
    
    public Response welcome() {
        return new WelcomeResponse();
    }
    
    public Response start() {
        return new StartResponse();
    }
    
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
            return  new EventCommand(stringCmd);
        default:
            throw new ForeignException("");
        }
    }
}
