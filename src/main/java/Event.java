public class Event extends Task {
    String time;
    public Event(String des, String time) {
        super(des, TaskType.EVENT);
        this.time = time;
    }

    public Event(String des, String time, boolean isDone) {
        super(des, TaskType.EVENT, isDone);
        this.time = time;
    }

    @Override
    public String parseTask() {
        return "E | " + Boolean.toString(isDone) + " | " + description + " | " + time;
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