package duke.command;

import duke.dukeexceptions.DukeException;
import duke.dukeexceptions.DukeTaskListException;
import duke.responses.Response;
import duke.responses.UnMarkResponse;

/***
 * Command that is created when user wants to Mark a Task as done.
 */
public class UnmarkCommand extends Command {

    /**
     * Constructors the Command using the user command.
     * @param stringCmd String representation of the users command.
     */
    public UnmarkCommand(String stringCmd) {
        this.stringCmd = stringCmd;
    }

    /**
     * Marks a task as undone from the TaskList and updates the Storage.
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
        taskList.unmarkTask(index - 1);
        return new UnMarkResponse(taskList.getTask(index - 1));
    }
}
