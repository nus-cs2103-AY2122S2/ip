public class Event extends Task {
    protected String at;

    public Event(int taskId, String name, String at) {
        super(taskId, name);
        this.at = at;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.at);
    }
}
