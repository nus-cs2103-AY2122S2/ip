public class Deadline extends Task{
    private String dueBy;

    public Deadline(String title, String dueBy) {
        super(title);
        this.dueBy = dueBy;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dueBy + ")";
    }
}
