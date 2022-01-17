public class Deadline extends Task {
    private String by;

    public Deadline(String task, String by) {
        super(TaskType.DEADLINE, task);
        this.by = by;
    }

    public String getBy() {
        return this.by;
    }
}
