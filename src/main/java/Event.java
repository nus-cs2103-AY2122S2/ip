public class Event extends Task {
    NotableDate date;

    public Event(String des, NotableDate date) {
        super(des, TaskType.EVENT);
        this.date = date;
    }

    @Override
    public String toString() {
        if (isDone) {
            return  "[E][X] " + description + " (at: " + date + ")";
        } else {
            return "[E][ ] " + description + " (at: " + date + ")";
        }
    }
}