package tasks;

public class Event extends task {
    public String timing;

    public Event (String name, String timing) {
        super(name);
        this.timing = timing;
    }

    @Override
    public String toString() {
        return this.name + "(" + this.timing + ")";
    }
}
