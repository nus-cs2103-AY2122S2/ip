public class Event extends Task {
    protected String at;

    Event(String taskName, String date) {
        super(taskName);
        this.at = date;
    }

    public String toSaveString() {
        return "E@@" + (this.isDone() ? "1@@" : "0@@")
                + this.name + "@@" + this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + String.format(" (at: %s)", this.at);
    }
}