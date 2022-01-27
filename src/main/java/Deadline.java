public class Deadline extends Task {

    protected DateHelper by;

    public Deadline(String description, DateHelper by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getTask() {
        return "[D]" + super.getTask() + "(by: " + by.getDatetime() + ")";
    }
}