public class Deadline extends Task {

    public String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline.trim();
    }

    public Deadline(String description, String deadline, boolean completed) {
        super(description, completed);
        this.deadline = deadline.trim();
    }

    public String toFile() {
        return "D -- " + super.toFile() + " -- " + this.deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline +  ")";
    }
}
