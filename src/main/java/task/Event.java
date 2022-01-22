package task;

public class Event extends Task {

    /**
     * Time at which this event occurs
     */
    private final String time;

    public Event(String name, String time) {
        super(name);
        this.time = time;
    }

    @Override
    public String getDescription() {
        return String.format("[E]%s %s (at: %s)",
                super.getDoneStatusCheckbox(), super.getName(), this.time);
    }

    @Override
    public String getSeralisedTaskData() {
        String doneString = this.isDone() ? "Y" : "N";
        return String.format("E,%s,%s,%s", super.getName(), doneString, this.time);
    }
}
