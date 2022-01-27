package main.java.duke.command;

import main.java.duke.dukeexceptions.DukeException;
import main.java.duke.responses.ListResponse;
import main.java.duke.responses.Response;

/***
 * Command that is created when user wants to list all the task in the 
 * TaskList
 */

public class ListCommand extends Command{

    /***
     * List out all the Task in the TaskList
     * @return Response class that would contains the UI message.
     * @throws DukeException
     */
    @Override
    public Response execute() throws DukeException {
        return new ListResponse(this.taskList);
    }
}
