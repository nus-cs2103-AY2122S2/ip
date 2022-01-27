package main.java.duke.command;

import main.java.duke.dukeexceptions.DukeException;
import main.java.duke.responses.Response;
import main.java.duke.responses.StopResponse;

/***
 * A Command that is run upon termination of the bot
 */
public class ByeCommand extends  Command {

    /***
     * Executes the command.
     * @return Response class that would contains the UI message.
     * @throws DukeException To catch possible errors.
     */
    @Override
    public Response execute() throws DukeException {
        return new StopResponse();
    }
}
