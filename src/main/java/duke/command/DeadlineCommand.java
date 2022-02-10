package duke.command;

import duke.dukeexceptions.DukeException;
import duke.responses.AddTaskResponse;
import duke.responses.DuplicateTaskResponse;
import duke.responses.Response;
import duke.task.Deadline;
import duke.task.Task;


/**
 * Command that is run when the user inputs a Deadline Task.
 */
public class DeadlineCommand extends Command {

    /**
     * Constructors the Command using the user command.
     * @param stringCmd String representation of the users command
     */
    public DeadlineCommand(String stringCmd) {
        this.stringCmd = stringCmd;
    }

    /**
     * Creates the Deadline Task and adds ot to the TaskList.
     * @return Response class that would contains the UI message.
     * @throws DukeException thrown in the event of a invalid command
     */
    @Override
    public Response execute() throws DukeException {
        String[] stringCmdUnits = this.stringCmd.split(" /by ");
        String taskName = stringCmdUnits[1];
        if (taskList.checkIfPresent(taskName)) {
            return new DuplicateTaskResponse(taskName);
        }
        Task tempTask = new Deadline(stringCmdUnits[0].replace("deadline ", ""),
                taskName);
        Integer oldTaskListLength = taskList.taskLength();
        this.taskList.addTask(tempTask);
        assert taskList.taskLength() == oldTaskListLength + 1 : "Add Deadline Task to list";
        this.store.loadToDisk(this.taskList);
        return new AddTaskResponse(tempTask, this.taskList);
    }
}
