package main.java.duke.command;

import main.java.duke.dukeexceptions.DukeException;
import main.java.duke.responses.AddTaskResponse;
import main.java.duke.responses.Response;
import main.java.duke.task.Deadline;
import main.java.duke.task.Task;

public class DeadlineCommand extends  Command {
    
    public DeadlineCommand(String stringCmd) {
        this.stringCmd = stringCmd;
    }
    
    @Override
    public Response execute() throws DukeException {
        String[] ans = this.stringCmd.split(" /by ");
        Task tempTask = new Deadline(ans[0].replace("deadline ", ""), ans[1]);
        this.taskList.addTask(tempTask);
        this.store.loadToDisk(this.taskList);
        return new AddTaskResponse(tempTask, this.taskList);
    }
}
