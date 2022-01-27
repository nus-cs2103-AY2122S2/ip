package main.java.duke.command;

import main.java.duke.dukeexceptions.DukeException;
import main.java.duke.dukeexceptions.DukeTaskListException;
import main.java.duke.responses.DeleteResponse;
import main.java.duke.responses.Response;

public class DeleteCommand extends  Command{
    
    public DeleteCommand(String stringCmd) {
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
        return  new DeleteResponse(this.taskList.removeTask(index - 1), taskList.taskLength());
    }
}
