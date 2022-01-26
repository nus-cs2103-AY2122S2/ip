package main.java.duke.command;

import main.java.duke.dukeexceptions.DukeException;
import main.java.duke.responses.ListResponse;
import main.java.duke.responses.Response;

public class ListCommand extends Command{
    @Override
    public Response execute() throws DukeException {
        return new ListResponse(this.taskList);
    }
}
