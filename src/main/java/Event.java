public class Event extends Task {

    protected String at;
    public boolean isDone;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.isDone = false;
    }

    public Event(String description, String at, boolean isDone) {
        super(description);
        this.at = at;
        this.isDone = isDone;
    }

    public String toSave() {
        int isDoneNumber;
        if(isDone) {
            isDoneNumber = 1;
        } else {
            isDoneNumber = 0;
        }
        return "E | " + isDoneNumber + " | " + description +
                    " | " + at + System.lineSeparator();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}