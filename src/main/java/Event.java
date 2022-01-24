import java.io.Serializable;

public class Event extends Task implements Serializable {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + "[" + super.getStatusIcon() + "] " + super.getDescription() + "(" + this.at + ")";
    }
}