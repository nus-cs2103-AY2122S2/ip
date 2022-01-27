public class Deadline extends Task {
    private String dueDate;

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String getSaveString() {
        return super.getSaveString() + "|" + dueDate;
    }

    @Override
    public String getIcon() {
        return "D";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (by: %s)", this.dueDate);
    }
}
