public class Event extends Task{
    String startDate;

    public Event (String task, String startDate) {
        super(task);
        this.startDate = startDate;
    }

    public String toString() {
        return String.format("[E]%s %s (at: %s)", this.statusString(), this.task, this.startDate);
    }
}
