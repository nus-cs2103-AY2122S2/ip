package tasks;

import exceptions.DateException;

public class Deadline extends Task {
    private String item;
    private String date;
    private boolean done;
    private String line = "-------------------------------------------";

    public Deadline(String details) throws DateException {
        String[] spliced = details.split("/by", 2);
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
            return "[D]"+"[X] " + item + "(by:" + date + ")";
        }
        else {
            return "[D]"+"[ ] "+ item + "(by:" + date + ")";
        }
    }
}
