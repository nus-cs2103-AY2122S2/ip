public class Deadline extends Task{
    String deadlineDate;

    public Deadline (String task, String deadlineDate) {
        super(task);
        this.deadlineDate = deadlineDate;
    }

    public String toString() {
        return String.format("[D]%s %s (by: %s)", this.statusString(), this.task, this.deadlineDate);
    }

}
