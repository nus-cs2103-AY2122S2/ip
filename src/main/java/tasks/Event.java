package tasks;

import exceptions.DateException;


public class Event extends Task {
    private String item;
    private String date;
    private boolean done;
    private String line = "-------------------------------------------";

    public Event(String details) throws DateException {
        String[] spliced = details.split("/at", 2);
        if (spliced.length == 1) {
            throw new DateException();
        }
        this.item = spliced[0];
        this.date = spliced[1];
    }

    @Override
    public void mark() {
        if (!this.done) {
            this.done = true;
            System.out.println(line);
            System.out.println("Done? Checked it off for you:");
            System.out.println(this);
            System.out.println(line);
        }

    }

    @Override
    public void unmark() {
        if (this.done) {
            this.done = false;
            System.out.println(line);
            System.out.println("Not done? Let me put it back for you:");
            System.out.println(this);
            System.out.println(line);
        }

    }

    @Override
    public String toString() {
        if (done) {
            return "[E]"+"[X] " + item + "(at:" + date + ")";
        }
        else {
            return "[E]"+"[ ] "+ item + "(at:" + date + ")";
        }
    }
}
