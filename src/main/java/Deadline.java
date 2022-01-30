import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String desc, LocalDateTime by) {
        super(desc, "D");
        this.by = by;
    }
    public Deadline(String desc, LocalDateTime by, boolean done) {
        super(desc, done, "D");
        this.by = by;
    }
    @Override
    public LocalDateTime getBy() {
        return this.by;
    }
    @Override
    public Task mark() {
        return new Deadline(this.desc, this.by, true);
    }
    @Override
    public Task unmark() {
        return new Deadline(this.desc, this.by, false);
    }

//    @Override
//    public String toString() {
//        return "[D]" + super.toString() + " (by: " + by + ")";
//    }
}
