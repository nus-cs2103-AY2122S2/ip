package main.java.duke.command;

import main.java.duke.dukeexceptions.DukeException;
import main.java.duke.dukeexceptions.DukeTaskListException;
import main.java.duke.responses.Response;
import main.java.duke.responses.UnMarkResponse;

public class UnmarkCommand extends  Command{

    public UnmarkCommand(String stringCmd) {
        this.stringCmd = stringCmd;
    }
    
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
