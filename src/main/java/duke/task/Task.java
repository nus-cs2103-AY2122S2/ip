package duke.task;

import java.time.LocalDate;

public class Task {

    private String s;
    private boolean isDone;
    private LocalDate date;

    public Task(String s) {
        this.s = s;
        this.isDone = false;
    }

    public void mark() {
        this.isDone = true;
        System.out.println("Duke: Nice! I've marked this task as done:\n      "+this.show());
    }

    public void unmark() {
        this.isDone = false;
        System.out.println("Duke: OK, I've marked this task as not done yet:\n      "+this.show());
    }

    public String show() {
        if(isDone) {
            return "[X] " + this.s;
        } else {
            return "[ ] " + this.s;
        }
    }

    public String storeFormat() {
        return this.s;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public String taskDescription() {
        return this.s;
    }

    public boolean getDone() {
        return this.isDone;
    }

    public void setDate(LocalDate e) {
        this.date = e;
    }
}
