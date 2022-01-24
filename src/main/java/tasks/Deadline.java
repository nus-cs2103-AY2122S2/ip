package tasks;

import exceptions.DateException;

public class Deadline extends Task {
    private String item;
    private String date;
    private boolean done;
    private String line = "-------------------------------------------";

    public Deadline(String details, String date) {
        this.item = details;
        this.date = date;
    }

    @Override
    public void mark() {
        if (!this.done) {
            this.done = true;
        }

    }

    @Override
    public void unmark() {
        if (this.done) {
            this.done = false;
        }

    }

    @Override
    public String toString() {
        if (done) {
            return "[D]"+"[X] " + item + "(by:" + date + ")";
        }
        else {
            return "[D]"+"[ ] "+ item + "(by:" + date + ")";
        }
    }
}
