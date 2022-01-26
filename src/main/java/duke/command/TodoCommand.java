package main.java.duke.command;

import main.java.duke.dukeexceptions.DukeException;
import main.java.duke.responses.AddTaskResponse;
import main.java.duke.responses.Response;
import main.java.duke.task.Task;
import main.java.duke.task.ToDo;

public class TodoCommand extends Command{
    
    public TodoCommand(String stringCmd) {
        this.stringCmd = stringCmd;
    }

    public Response execute() throws DukeException {
        String[] stringCmdArr = this.stringCmd.split("todo");
        Task tempTask = new ToDo(stringCmdArr[1]);
        this.taskList.addTask(tempTask);
        store.loadToDisk(this.taskList);
        return new AddTaskResponse(tempTask, this.taskList);
    }
}
