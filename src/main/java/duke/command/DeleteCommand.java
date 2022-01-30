package duke.command;

import duke.dukeexceptions.DukeException;
import duke.dukeexceptions.DukeTaskListException;
import duke.responses.DeleteResponse;
import duke.responses.Response;


/**
 * Command that is created when user wants to delete a Task.
 */
public class DeleteCommand extends Command {

    /**
     * Constructors the Command using the user command.
     * @param stringCmd String representation of the users command.
     */
    public DeleteCommand(String stringCmd) {
        this.stringCmd = stringCmd;
    }

    /**
     * Deletes a task from the TaskList and updates the Storage.
     * @return Response class that would contains the UI message.
     * @throws DukeException thrown in the event of a invalid index.
     */
    @Override
    public Response execute() throws DukeException {
        String[] stringCmdUnits = stringCmd.split(" ");
        if (stringCmdUnits.length == 1) {
            throw new DukeTaskListException("");
        }
        int index = Integer.parseInt(stringCmdUnits[1]);
        if (index > taskList.taskLength() || index < 1) {
            throw new DukeTaskListException("");
        }
        return new DeleteResponse(this.taskList.removeTask(index - 1), taskList.taskLength());
    }
}
