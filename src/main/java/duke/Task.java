package duke;

import java.time.LocalDate;

//Task class to save description of tasks and mark them as done or undone
public class Task {
    protected String description;
    protected boolean isDone;
    protected LocalDate date;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, LocalDate date) {
        this.description = description;
        this.isDone = false;
        this.date = date;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    //marks a task as done and notifies the user
    public void markAsDone() {
        isDone = true;
        System.out.println("Nice! I've marked this task as done:\n" + this.getStatusIcon() + " " + this.description);
    }

    //mark a task as undone and notifies the user
    public void markAsUndone(){
        isDone = false;
        System.out.println("Ok! I've marked this task as not done yet:\n" + this.getStatusIcon() + " "
                + this.description);
    }
}
