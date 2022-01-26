public class Event extends Task {

    public Event(String taskType, String description, String date) {
        super(taskType, description, date);
    }

    public Event(String taskType, boolean isDone, String description, String date) {
        super(taskType, isDone, description, date);
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + super.getDate() + ")";
    }
}
