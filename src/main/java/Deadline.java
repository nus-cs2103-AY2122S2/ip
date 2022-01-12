public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }

    @Override
    public void markAsDone() {
        super.markAsDone();
        System.out.println(super.markAsDoneFeedback + this);
    }

    @Override
    public void markAsUndone() {
        super.markAsUndone();
        System.out.println(super.markAsUndoneFeedback + this);
    }
}