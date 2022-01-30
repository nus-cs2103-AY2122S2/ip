package duke.command;

import duke.dukeexceptions.DukeException;
import duke.responses.AddTaskResponse;
import duke.responses.Response;
import duke.task.Task;
import duke.task.ToDo;


/**
 * Command that is run when the user inputs a Tdo Task.
 */
public class TodoCommand extends Command {

    /**
     * Constructors the Command using the user command.
     * @param stringCmd String representation of the users command.
     */
    public TodoCommand(String stringCmd) {
        this.stringCmd = stringCmd;
    }

    /**
     * Creates the Todo Task and adds ot to the TaskList.
     * @return Response class that would contains the UI message.
     * @throws DukeException thrown in the event of a invalid command.
     */
    @Override
    public Response execute() throws DukeException {
        String[] stringCmdUnits = stringCmd.split("todo ");
        Task tempTask = new ToDo(stringCmdUnits[1]);
        taskList.addTask(tempTask);
        store.loadToDisk(taskList);
        return new AddTaskResponse(tempTask, taskList);
    }
}
