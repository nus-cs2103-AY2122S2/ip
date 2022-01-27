public class Event extends Task{

    protected String at;

    public Event(boolean status, String description, String at) {
        super(description);
        this.at = at;
        super.isDone = status;
    }

    @Override
    public String appendtoFile() {
        return "E|" + (super.isDone ? "1" : "0") + "|" + super.description + "|" + this.at + "\n";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
