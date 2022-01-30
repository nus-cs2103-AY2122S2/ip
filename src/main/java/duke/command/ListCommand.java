package duke.command;

import duke.dukeexceptions.DukeException;
import duke.responses.ListResponse;
import duke.responses.Response;

/**
 * Command that is created when user wants to list all the task in the TaskList.
 */
public class ListCommand extends Command {

    /**
     * List out all the Task in the TaskList.
     * @return Response class that would contains the UI message.
     * @throws DukeException throws possible duke exceptions.
     */
    @Override
    public Response execute() throws DukeException {
        return new ListResponse(this.taskList);
    }
}
