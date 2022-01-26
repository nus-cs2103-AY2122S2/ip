package main.java.duke.responses;

import main.java.duke.task.Task;

public class MarkResponse implements Response {
    Task currTask;
    
    public MarkResponse(Task currTask) {
        this.currTask = currTask;    
    }

    @Override
    public void callback() {
        System.out.println(
                "____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:\n");
        System.out.println(this.currTask.display());
        System.out.println(
                "____________________________________________________________");
    }
}
