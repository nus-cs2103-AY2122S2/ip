public class Event extends Task {
    protected String at;

    Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toTxtString() {
        String marked = this.isDone? "1" : "0";
        String txtString = "event-" + marked + "-" + this.description + "-" + this.at;
        return txtString;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}
