package juke.task;

import juke.DateTimeHandler;

public class Event extends Task {
    private DateTimeHandler date;
    
    public Event(String description, String time) {
        super(description);
        this.date = new DateTimeHandler(time);
    }
    
    public String getTime() {
        return this.date.getDateTime();
    }
    
    @Override
    String getTaskIcon() {
        return "[E]";
    }
    
    @Override
    public String toString() {
        return super.toString() + " (at: " + this.getTime() + ")";
    }
}
