import java.util.Objects;

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
    public boolean equals(Object obj){
        if (obj != null && obj.getClass() == getClass()) {
            Deadline deadline = (Deadline) obj;
            return (deadline.description.equals(this.description) && deadline.by.equals(this.by));
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, by);
    }
}