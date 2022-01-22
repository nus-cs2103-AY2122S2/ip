package duke.tasks;

public class Event extends Task {
    public String timing;

    public Event (String name, String timing) {
        super(name);
        this.timing = timing;
        this.info = "E,0," + name + "," + timing;
    }

    @Override
    public void mark() {
        this.isDone = true;
        this.info = "E,1," + name + "," + timing;
    }

    @Override
    public void unmark() {
        this.isDone = false;
        this.info = "E,0," + name + "," + timing;
    }

    @Override
    public String toString() {
        return this.name + "(" + this.timing + ")";
    }
}
