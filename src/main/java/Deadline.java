public class Deadline extends Task{
    String by;

    public Deadline(String taskName, String by) {
        super(taskName);
        this.by = by;
    }

    @Override
    public String toString() {
        String done = getStatus() ? "[X]" : "[ ]";
        return "[D]" + done + getTaskName() + " (by: " + this.by + ")";
    }

}
