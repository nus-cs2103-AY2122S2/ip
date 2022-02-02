package duke;

import java.time.format.DateTimeFormatter;

public class Task {
    protected String d;
    protected String type;
    protected boolean isDone;
    protected final DateTimeFormatter OUT_DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    public Task(String d) {
        this.d = d;
        this.isDone = false;
    }

    public Task(String d, String done){
        this.d = d;
        if(done.equals("1")) {
            this.isDone = true;
        } else {
            this.isDone = false;
        }
    }

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