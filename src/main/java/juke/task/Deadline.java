package juke.task;

import juke.DateTimeHandler;

public class Deadline extends Task {
    private DateTimeHandler date;
    
    public Deadline(String description, String time) {
        super(description);
        this.date = new DateTimeHandler(time);
    }
    
    public String getTime() {
        return this.date.getDateTime();
    }
    
    @Override
    public String getTaskIcon() {
        return "[D]";
    }
    
    @Override
    public String toString() {
        return super.toString() + " (by: " + this.getTime() + ")";
    }
}
