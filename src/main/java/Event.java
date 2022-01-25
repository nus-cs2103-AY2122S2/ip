import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime at;

    public Event(String description, LocalDateTime time) {
        super(description, Type.EVENT);
        this.at = time;
    }

    public String getAt() {
        return this.at.toString();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.at.format(Const.OUT_TIME_FORMATTER) + ")";
    }
}
