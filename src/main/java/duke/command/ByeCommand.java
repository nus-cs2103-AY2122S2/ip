package main.java.duke.command;

import main.java.duke.dukeexceptions.DukeException;
import main.java.duke.responses.Response;
import main.java.duke.responses.StopResponse;

public class ByeCommand extends  Command {
    
    @Override
    public Response execute() throws DukeException {
        return new StopResponse();
    }
}
