import java.util.Objects;

public class Deadline extends Task {
    protected String by;
    protected String taskType;

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