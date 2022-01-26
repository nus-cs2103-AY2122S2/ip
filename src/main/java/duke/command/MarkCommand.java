package main.java.duke.command;

import main.java.duke.dukeexceptions.DukeException;
import main.java.duke.responses.MarkResponse;
import main.java.duke.responses.Response;

public class MarkCommand extends Command{
    
    public MarkCommand(String stringCmd) {
        this.stringCmd = stringCmd;
    }
    
    @Override
    public Response execute() throws DukeException {
        String[] stringCmdArr = stringCmd.split(" ");
        int index = Integer.parseInt(stringCmdArr[1]);
        this.taskList.markTask(index - 1);
        return new MarkResponse(this.taskList.getTask(index - 1));
    }
}
