package main.java.responses;


import main.java.task.Task;

public class UnMarkResponse implements Response {
    Task currTask;

    public UnMarkResponse(Task currTask) {
        this.currTask = currTask;
    }
    

    @Override
    public void callback() {
        System.out.println(
                "____________________________________________________________");
        System.out.println("OK, I've marked this task as not done yet:\n");
        System.out.println(this.currTask.display());
        System.out.println(
                "____________________________________________________________");
    }
}
