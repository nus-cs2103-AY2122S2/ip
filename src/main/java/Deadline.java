public class Deadline extends Task {
    protected String by;

    Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toTxtString() {
        String marked = this.isDone? "1" : "0";
        String txtString = "deadline-" + marked + "-" + this.description + "-" + this.by;
        return txtString;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
