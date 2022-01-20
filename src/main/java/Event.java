public class Event extends Task{
    String eventDate;

    public Event(String name, boolean isChecked, String taskLabel, String eventDate) {
        super(name, isChecked, taskLabel);
        this.eventDate = eventDate;
    }

    public String isTaskCheck() {
        return super.getChecked() ? "X" : " ";
    }

    @Override
    public String toString() {
        return "[" + super.getTaskLabel() + "][" + isTaskCheck() + "] " + super.toString() + " (at: " + this.eventDate + ")";
    }
}