public class Event extends Task{
    String startDate;

    public Event (String task, String startDate) {
        super(task);
        this.startDate = startDate;
    }

    public Event (String task, boolean isDone, String startDate) {
        super(task, isDone);
        this.startDate = startDate;
    }

    public String toFileFormat() {
        Integer i = this.isDone ? 1 : 0;
        return String.format("E | %d | %s | %s\n", i, this.task, startDate);
    }

    @Override
    public String toString() {
        return String.format("[E]%s %s (at: %s)", this.statusString(), this.task, this.startDate);
    }
}
