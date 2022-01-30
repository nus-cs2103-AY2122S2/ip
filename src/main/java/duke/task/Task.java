package duke.task;

import java.time.LocalDate;

public class Task {

    public String s;
    public boolean done;
    public LocalDate date;

    /**
     * Creates a Task instance
     * @param s Description of the task
     */
    public Task(String s){
        this.s = s;
        this.done = false;
    }

    /**
     * Marks current task as done by setting done as true
     */
    public void mark(){
        this.done = true;
        System.out.println("Duke: Nice! I've marked this task as done:\n      "+this.show());
    }

    /**
     * Unmarks current task by setting done as false
     */
    public void unmark(){
        this.done = false;
        System.out.println("Duke: OK, I've marked this task as not done yet:\n      "+this.show());
    }

    /**
     * Returns a string representation of the task
     * @return String representation of this task
     */
    public String show(){
        if(done){
            return "[X] " + this.s;
        }
        else{
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

}
