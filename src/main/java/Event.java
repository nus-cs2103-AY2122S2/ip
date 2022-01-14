import java.util.Objects;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at + ")";
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