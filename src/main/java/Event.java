public class Event extends Task {
    private String at;

    public Event(String task, String at) {
        super(TaskType.EVENT, task);
        this.at = at;
    }

    public String getAt() {
        return this.at;
    }
}
