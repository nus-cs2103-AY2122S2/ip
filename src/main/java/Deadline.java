public class Deadline extends Task{
    String deadlineDate;

    public Deadline (String task, String deadlineDate) {
        super(task);
        this.deadlineDate = deadlineDate;
    }

    public Deadline (String task, boolean isDone, String deadlineDate) {
        super(task, isDone);
        this.deadlineDate = deadlineDate;
    }

    public String toFileFormat() {
        Integer i = this.isDone ? 1 : 0;
        return String.format("D | %d | %s | %s\n", i, this.task, deadlineDate);
    }

    @Override
    public String toString() {
        return String.format("[D]%s %s (by: %s)", this.statusString(), this.task, this.deadlineDate);
    }

}
