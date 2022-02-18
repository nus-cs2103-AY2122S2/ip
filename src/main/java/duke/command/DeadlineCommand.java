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
     * Constructs the Deadline Command using the user command.
     * @param stringCmd String representation of the users command
     */
    public DeadlineCommand(String stringCmd) {
        this.stringCmd = stringCmd;
    }

    /**
     * Create the Deadline Task and adds ot to the TaskList.
     * @return Response class that would contains the UI message.
     * @throws DukeException thrown in the event of a invalid command
     */
    @Override
    public Response execute() throws DukeException {
        String[] stringCmdUnits = stringCmd.split(" /by ");
        String taskName = stringCmdUnits[0].replace("deadline ", "");
        String dateInfo = stringCmdUnits[1];
        System.out.println(dateInfo);
        if (taskList.checkIfPresent(taskName)) {
            return new DuplicateTaskResponse(taskName);
        }
        Task tempTask = new Deadline(taskName, dateInfo);
        Integer oldTaskListLength = taskList.taskLength();
        this.taskList.addTask(tempTask);
        assert taskList.taskLength() == oldTaskListLength + 1 : "Add Deadline Task to list";
        this.store.loadToDisk(this.taskList);
        return new AddTaskResponse(tempTask, this.taskList);
    }
}
