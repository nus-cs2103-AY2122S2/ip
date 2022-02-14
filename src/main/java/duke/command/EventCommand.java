package duke.command;

import duke.dukeexceptions.DukeException;
import duke.responses.AddTaskResponse;
import duke.responses.DuplicateTaskResponse;
import duke.responses.Response;
import duke.task.Event;
import duke.task.Task;

/**
 * Command that is run when the user inputs a Event Task.
 */
public class EventCommand extends Command {

    /**
     * Constructors the Command using the user command.
     * @param stringCmd String representation of the users command.
     */
    public EventCommand(String stringCmd) {
        this.stringCmd = stringCmd;
    }

    /**
     * Creates the Event Task and adds ot to the TaskList.
     * @return Response class that would contains the UI message.
     * @throws DukeException thrown in the event of a invalid command.
     */
    @Override
    public Response execute() throws DukeException {
        String[] stringCmdUnits = stringCmd.split(" /at ");
        String taskName = stringCmdUnits[0].replace("event ", "");
        String dateInfo = stringCmdUnits[1];
        if (taskList.checkIfPresent(taskName)) {
            return new DuplicateTaskResponse(taskName);
        }
        Task tempTask = new Event(taskName, dateInfo);
        Integer oldTaskListLength = taskList.taskLength();
        this.taskList.addTask(tempTask);
        assert taskList.taskLength() == oldTaskListLength + 1 : "Add Event Task to list";
        store.loadToDisk(this.taskList);
        return new AddTaskResponse(tempTask, this.taskList);
    }
}
