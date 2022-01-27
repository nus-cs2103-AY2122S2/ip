package main.java.duke.command;

import main.java.duke.data.Storage;
import main.java.duke.data.TaskList;
import main.java.duke.dukeexceptions.DukeException;
import main.java.duke.responses.Response;

/***
 * The base command class.
 */
public class Command {

    protected  Storage store;
    protected  TaskList taskList;
    protected String stringCmd;

    /***
     * Executes the command.
     * @return Response class that would contains the UI message.
     * @throws DukeException To catch possible errors.
     */
    
    public Response execute() throws DukeException {
        return  null;
    };

    /***
     * get function to get the Storage and TaskList
     * @param store persist Storage
     * @param taskList local Taskist
     */
    
    public void getReasources(Storage store, TaskList taskList) {
        this.store = store;
        this.taskList = taskList;
    }
}
