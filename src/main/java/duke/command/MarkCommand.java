package main.java.duke.command;

import main.java.duke.dukeexceptions.DukeException;
import main.java.duke.dukeexceptions.DukeTaskListException;
import main.java.duke.responses.MarkResponse;
import main.java.duke.responses.Response;

public class MarkCommand extends Command{
    
    public MarkCommand(String stringCmd) {
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
        this.taskList.markTask(index - 1);
        this.store.loadToDisk(this.taskList);
        return new MarkResponse(this.taskList.getTask(index - 1));
    }
}
