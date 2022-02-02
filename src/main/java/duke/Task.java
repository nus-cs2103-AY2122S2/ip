package duke;

import java.time.format.DateTimeFormatter;

/**
 * parent class for task
 */
public class Task {
    protected String d;
    protected String type;
    protected boolean isDone;
    protected final DateTimeFormatter OUT_DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * constructor for Task
     * @param d task descriptiom
     */
    public Task(String d) {
        this.d = d;
        this.isDone = false;
    }

    /**
     * constructor for Task
     * @param d task description
     * @param done check if task is done
     */
    public Task(String d, String done){
        this.d = d;
        if(done.equals("1")) {
            this.isDone = true;
        } else {
            this.isDone = false;
        }
    }

    /**
     * method to return the description of the task
     * @return A string representing the description
     */
    public String getDescription(){
        return this.d;
    }

    public String getIsDone(){
        if(isDone){
            return "1";
        }
        else{
            return "0";
        }
    }

    public String getType(){
        return this.type;
    }

    public String markString() {
        return (isDone ? "[X]" : "[]");
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setUndone(){
        this.isDone = false;
    }

    @Override
    public String toString() {
        return markString() + " " + d;
    }
}