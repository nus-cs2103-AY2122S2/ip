package duke2;// package duke1;

public class Task {
    protected boolean done = false;
    protected String name;

    Task(String name) {
        this.name = name;
    }

    public String mark() {
        this.done = true;
        return "Nice! I've marked this task as done:\n" + this.name + "\n";

    }

    public String unmark() {
        this.done = false;
        return "OK, I've marked this task as not done yet:\n" + this.name + "\n";

    }

    public void changeDescription(String str) {
        this.name = str;
    }

    public String toString() {
        if (done) {
            return "[X] " + this.name;
        }
        return "[] " + this.name;
    }
}