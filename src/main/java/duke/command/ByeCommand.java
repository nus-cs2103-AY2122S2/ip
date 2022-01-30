package duke.command;

import duke.dukeexceptions.DukeException;
import duke.responses.Response;
import duke.responses.StopResponse;

/**
 * A Command that is run upon termination of the bot.
 */
public class ByeCommand extends Command {

    /**
     * Executes the command.
     * @return Response class that would contains the UI message.
     * @throws DukeException To catch possible errors.
     */
    @Override
    public Response execute() throws DukeException {
        return new StopResponse();
    }
}
