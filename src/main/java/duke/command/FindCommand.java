package duke.command;

import duke.data.TaskList;
import duke.dukeexceptions.DukeException;
import duke.responses.FindResponse;
import duke.responses.Response;

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
