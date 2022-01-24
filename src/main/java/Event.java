public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }

    /**
     * Returns the save format in String of this Task object
     * @return A String for the save format of this Task object
     */
    @Override
    public String getSaveFormat() {
        return "E," + ((isDone ? "1" : "0")) + "," + super.getSaveFormat() + "," + this.at;
    }
}
