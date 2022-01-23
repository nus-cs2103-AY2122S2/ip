public class Event extends Task {
    String type = "E";
    String dateAndTime;

    Event(String description, String dateAndTime) {
        super(description);
        this.dateAndTime = dateAndTime;
    }

    @Override
    public String toString() {
        return "[" + this.type + "]" + super.toString() + " (at: "+ this.dateAndTime + ")";
    }
}