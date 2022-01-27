public class Deadline extends Task {
    protected DateHelper by;
    private String type;

    public Deadline(String description, DateHelper by) {
        super(description);
        this.by = by;
        this.type = "D";
    }

    @Override
    public String getTask() {
        return "[" + this.type +"]" + super.getTask() + "(by: " + by.getDatetime() + ")";
    }

    @Override
    public String getDescription() {
        return this.type + " | " + this.description + " | " + by.getDatetime();
    }
}