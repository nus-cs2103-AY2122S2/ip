public class Event extends Task{
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String formatString() {
        String output = "E";
        String markState = this.isDone ? "mark" : "unmark";
        return output + " | " + markState + " | "
                + this.description + " | " + this.at;

    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}
