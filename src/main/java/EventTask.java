public class EventTask extends Task {
    private String time;

    public EventTask(String taskName, String time) {
        super(taskName);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[E]" + super.toString() + " (%s)", this.time.replaceFirst(" ", ": "));
    }
}
