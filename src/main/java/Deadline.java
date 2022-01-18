public class Deadline extends Task {
    public Deadline(String description, String dueDate) {
        super(description, dueDate);
    }

    @Override
    public String toString() {
        return "[D][" + super.getStatusIcon() + "] " + this.description + " (by: " + this.date + ")";
    }
}
