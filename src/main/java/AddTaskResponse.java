package main.java;

import java.util.ArrayList;

/**
 * Response when added a Task
 */

public class AddTaskResponse implements Response{
    Task currTask;
    ArrayList<Task> tasklist;

    /**
     * Constructor for the AddTaskResponse.
     * @param currTask Task that is created.
     * @param tasklist The List of Task.
     */

    AddTaskResponse(Task currTask, ArrayList<Task> tasklist) {
        this.currTask = currTask;
        this.tasklist = tasklist;
    }

    /**
     * Callback function that displays the intended results.
     */
    @Override
    public void callback() {
        System.out.println(
                "____________________________________________________________"
        );

        System.out.println(
                "Got it. I've added this task:"
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
