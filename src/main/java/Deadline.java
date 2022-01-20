public class Deadline extends Task{
    private String dueDate;

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    @Override
    public String toString() {
        return "[D] [" + this.getStatusIcon() + "] " + super.getDescription() + " (" + this.getDueDate() + ")";
    }
}
