package main.java.duke.command;

import main.java.duke.data.TaskList;
import main.java.duke.dukeexceptions.DukeException;
import main.java.duke.responses.FindResponse;
import main.java.duke.responses.Response;

public class FindCommand extends Command {
    
    public FindCommand(String stringCmd) {
        this.stringCmd = stringCmd;
    }

    @Override
    public Response execute() throws DukeException {
        TaskList tempList = new TaskList();
        String[] stringCmdUnits = stringCmd.split(" ");
        for (int i = 0; i < taskList.taskLength(); i++) {
            if (taskList.getTask(i).isRelated(stringCmdUnits[1])) {
                tempList.addTask(taskList.getTask(i));
            }
        }
        return new FindResponse(tempList);
    }
}
