public class Deadline extends Task {
    protected String deadline;

    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return super.toString() + "(by: " + this.deadline + ")";
    }

    @Override
    public String getCurrentStatus() {
        return "[D]" + super.getCurrentStatus();
    }

    @Override
    public String getType() {
        return "Deadline Task";
    }
}
