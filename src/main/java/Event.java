public class Event extends Task{
    String eventDate;

    public Event(String name, boolean isChecked, String taskLabel, String eventDate) {
        super(name, isChecked, taskLabel);
        this.eventDate = eventDate;
    }

    @Override
    public String toString() {
        return "[" + super.getTaskLabel() + "][" + super.isTaskCheck() + "] " + super.toString() + " (at: " + this.eventDate + ")";
    }
}