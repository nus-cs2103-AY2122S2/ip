package main.java.duke.command;

import main.java.duke.dukeexceptions.DukeException;
import main.java.duke.responses.AddTaskResponse;
import main.java.duke.responses.Response;
import main.java.duke.task.Event;
import main.java.duke.task.Task;

public class EventCommand extends  Command{
    
    public EventCommand(String stringCmd) {
        this.stringCmd = stringCmd;
    }
    
    @Override
    public Response execute() throws DukeException {
        String[] stringCmdArr = this.stringCmd.split(" ");
        String[] ans = stringCmd.split(" /at ");
        Task tempTask = new Event(ans[0].replace("deadline ", ""), ans[1]);
        this.taskList.addTask(tempTask);
        store.loadToDisk(this.taskList);
        return new AddTaskResponse(tempTask, this.taskList);
    }
}
