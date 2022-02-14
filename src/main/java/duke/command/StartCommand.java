package duke.command;

import duke.dukeexceptions.DukeException;
import duke.responses.Response;
import duke.responses.WelcomeResponse;

/**
 * Command that is created when user wants to Mark a Task as done.
 */

public class StartCommand extends Command {

    /**
     * Creates the welcome pesponse.
     * @return Response class that would contains the UI message.
     */
    @Override
    public Response execute() throws DukeException {
        return new WelcomeResponse();
    }
}
