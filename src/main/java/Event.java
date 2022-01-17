public class Event extends Task{
    String at;

    public Event(String taskName, String at) {
        super(taskName);
        this.at = at;
    }

    @Override
    public String toString() {
        String done = getStatus() ? "[X]" : "[ ]";
        return "[E]" + done + getTaskName() + " (at: " + this.at + ")";
    }

}
