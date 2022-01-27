package main.java.duke.command;

import main.java.duke.data.Storage;
import main.java.duke.data.TaskList;
import main.java.duke.dukeexceptions.DukeException;
import main.java.duke.responses.Response;

public class Command {
    
    protected Storage store;
    protected TaskList taskList;
    protected String stringCmd;
    
    public Response execute() throws DukeException {
        return  null;
    };
    
    public void getResources(Storage store, TaskList taskList) {
        this.store = store;
        this.taskList = taskList;
    }
}
