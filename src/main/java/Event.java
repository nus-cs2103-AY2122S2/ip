public class Event extends Task {
    String time;
    public Event(String des, String time) {
        super(des, TaskType.EVENT);
        this.time = time;
    }

    @Override
    public String toString() {
        if (isDone) {
            return  "[E][X] " + description + " (at: " + time + ")";
        } else {
            return "[E][ ] " + description + " (at: " + time + ")";
        }
    }
}