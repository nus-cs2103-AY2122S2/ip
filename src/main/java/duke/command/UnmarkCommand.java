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
        String[] stringCmdArr = stringCmd.split(" ");
        if (stringCmdArr.length == 1) {
            throw new DukeTaskListException("");
        }
        int index = Integer.parseInt(stringCmdArr[1]);
        if (index > taskList.taskLength() || index < 1) {
            throw new DukeTaskListException("");
        }
        this.taskList.unmarkTask(index - 1);
        return new UnMarkResponse(this.taskList.getTask(index - 1));
    }
}
