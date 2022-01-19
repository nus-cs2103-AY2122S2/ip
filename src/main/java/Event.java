public class Event extends Task {
    private String eventTime;

    public Event() {
        super();

        this.eventTime = "";
    }

    public Event(String taskDescription, String eventTime) {
        super(taskDescription);

        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        sb.setLength(0);
        sb.append("[E]").append("[").append(this.isDone ? "X] " : " ] ").append(this.taskDescription);
        sb.append("\n (at: ").append(this.eventTime).append(")");

        return sb.toString();
    }
}
