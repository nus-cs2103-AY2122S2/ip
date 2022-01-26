package main.java.duke.command;

import main.java.duke.dukeexceptions.DukeException;
import main.java.duke.responses.Response;
import main.java.duke.responses.UnMarkResponse;

public class UnmarkCommand extends  Command{

    public UnmarkCommand(String stringCmd) {
        this.stringCmd = stringCmd;
    }
    
    @Override
    public Response execute() throws DukeException {
        String[] stringCmdArr = stringCmd.split(" ");
        int index = Integer.parseInt(stringCmdArr[1]);
        this.taskList.markTask(index - 1);
        return new UnMarkResponse(this.taskList.getTask(index - 1));
    }
}
