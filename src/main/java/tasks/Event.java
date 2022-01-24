package tasks;

import exceptions.DateException;


public class Event extends Task {
    private String item;
    private String date;
    private boolean isDone;

    public Event(String details, String date) {
        this.item = details;
        this.date = date;
    }

    @Override
    public void mark() {
        if (!this.isDone) {
            this.isDone = true;
        }

    }

    @Override
    public void unmark() {
        if (this.isDone) {
            this.isDone = false;
        }

    }

    @Override
    public String toString() {
        if (isDone) {
            return "[E]"+"[X] " + item + "(at:" + date + ")";
        }
        else {
            return "[E]"+"[ ] "+ item + "(at:" + date + ")";
        }
    }
}
