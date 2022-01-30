package duke.task;

import java.time.LocalDate;

public class Task {

    private String s;
    private boolean isDone;
    private LocalDate date;

    /**
     * Creates a Task instance
     * @param s Description of the task
     */
    public Task(String s){
        this.s = s;
        this.isDone = false;
    }

    /**
     * Marks current task as done by setting done as true
     */
    public void mark() {
        this.isDone = true;
        System.out.println("Duke: Nice! I've marked this task as done:\n      "+this.show());
    }

    /**
     * Unmarks current task by setting done as false
     */
    public void unmark() {
        this.isDone = false;
        System.out.println("Duke: OK, I've marked this task as not done yet:\n      "+this.show());
    }

    /**
     * Returns a string representation of the task
     * @return String representation of this task
     */
    public String show() {
        if(isDone) {
            return "[X] " + this.s;
        } else {
            return "[ ] " + this.s;
        }
    }

    /**
     * returns String format of the task to store
     * @return String format of the task to store
     */
    public String storeFormat(){
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
