public class Event extends Task{
    private String eventDate;
    private Time time;

    public Event(String name, boolean isChecked, String taskLabel, String eventDate) {
        super(name, isChecked, taskLabel);
        Time time = new Time(eventDate);
        this.time = time;

    }

    @Override
    public String toString() {
        return "[" + super.getTaskLabel() + "][" + super.isTaskCheck() + "] " + super.toString() +
                " (at: " + this.time + ")";
    }
}