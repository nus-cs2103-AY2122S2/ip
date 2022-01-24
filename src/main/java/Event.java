public class Event extends Task {
    protected String at;

    public Event(String desc, String at) {
        super(desc, TaskType.EVENT);
        this.at = at;
    }

    public String getAt() {
        return at;
    }

    @Override
    public String toString() {
        return getType() + super.toString() + " (At: " + at + ")";
    }
}
