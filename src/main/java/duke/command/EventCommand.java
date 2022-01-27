package main.java.duke.command;

import main.java.duke.dukeexceptions.DukeException;
import main.java.duke.responses.AddTaskResponse;
import main.java.duke.responses.Response;
import main.java.duke.task.Event;
import main.java.duke.task.Task;

/***
 * Command that is run when the user inputs a Event Task.
 */

public class EventCommand extends  Command{

    /***
     * Constructors the Command using the user command 
     * @param stringCmd String representation of the users command
     */
    
    public EventCommand(String stringCmd) {
        this.stringCmd = stringCmd;
    }

    /***
     * Creates the Event Task and adds ot to the TaskList
     * @return Response class that would contains the UI message.
     * @throws DukeException thrown in the event of a invalid command
     */
    @Override
    public Response execute() throws DukeException {
        String[] stringCmdUnits = stringCmd.split(" /at ");
        Task tempTask = new Event(stringCmdUnits[0].replace("event ", ""), stringCmdUnits[1]);
        this.taskList.addTask(tempTask);
        store.loadToDisk(this.taskList);
        return new AddTaskResponse(tempTask, this.taskList);
    }
}
