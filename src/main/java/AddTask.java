package main.java;

import java.util.ArrayList;

public class AddTask implements Response{
    Task currTask;
    ArrayList<Task> tasklist;

    AddTask(Task currTask, ArrayList tasklist) {
        this.currTask = currTask;
        this.tasklist = tasklist;
    }
    @Override
    public void callback() {
        System.out.println(
                "____________________________________________________________"
        );

        System.out.println(
                "Got it. I've added this task: "
        );

        System.out.println(
                currTask.display()
        );

        System.out.println(
                "Now you have " + tasklist.size() + " tasks in this list"
        );

        System.out.println(
                "____________________________________________________________"
        );
    }
}
