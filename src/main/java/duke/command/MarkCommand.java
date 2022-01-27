package main.java.duke.command;

import main.java.duke.dukeexceptions.DukeException;
import main.java.duke.dukeexceptions.DukeTaskListException;
import main.java.duke.responses.MarkResponse;
import main.java.duke.responses.Response;

/***
 * Command that is created when user wants to Mark a Task as done
 */

public class MarkCommand extends Command{

    /***
     * Constructors the Command using the user command 
     * @param stringCmd String representation of the users command
     */

    public MarkCommand(String stringCmd) {
        this.stringCmd = stringCmd;
    }
    /***
     * Marks a task as done from the TaskList and updates the Storage.
     * @return Response class that would contains the UI message.
     * @throws DukeException thrown in the event of a invalid index.
     */
    @Override
    public Response execute() throws DukeException {
        String[] stringCmdArr = stringCmd.split(" ");
        if (stringCmdArr.length == 1) {
            throw new DukeTaskListException("");
        }
        int index = Integer.parseInt(stringCmdArr[1]);
        if (index > taskList.taskLength() || index < 1) {
            throw new DukeTaskListException("");
        }
        this.taskList.markTask(index - 1);
        this.store.loadToDisk(this.taskList);
        return new MarkResponse(this.taskList.getTask(index - 1));
    }
}
