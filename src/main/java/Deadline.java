public class Deadline extends Task {
    protected String by;

    public Deadline(String s, String by) {
        super(s);
        this.by = by;
    }
    
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
} 