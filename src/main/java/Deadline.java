public class Deadline extends Task {

    protected String by;
    
    public Deadline(String description, String by, boolean done) {
        super(description, done);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toStringSaveData() {
        return String.join(" | ", "D", String.valueOf(done ? 1 : 0), description, by);
    }

}
