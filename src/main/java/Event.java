import java.util.Objects;

public class Event extends Task {
    protected String at;
    protected String taskType;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at + ")";
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
            Event event = (Event) obj;
            return (event.description.equals(this.description) && event.at.equals(this.at));
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, at);
    }
}