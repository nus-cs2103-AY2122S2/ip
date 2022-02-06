package duke.command;

import duke.dukeexceptions.DukeException;
import duke.responses.AddTaskResponse;
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
        Task tempTask = new Event(stringCmdUnits[0].replace("event ", ""), stringCmdUnits[1]);
        Integer oldTaskListLength = taskList.taskLength();
        this.taskList.addTask(tempTask);
        assert taskList.taskLength() == oldTaskListLength + 1 : "Add Event Task to list";
        store.loadToDisk(this.taskList);
        return new AddTaskResponse(tempTask, this.taskList);
    }
}
