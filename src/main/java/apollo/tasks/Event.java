package apollo.tasks;

import apollo.parser.Parser;

import java.time.LocalDateTime;

public class Event extends Task {
    private final LocalDateTime period;

    public Event(String description, LocalDateTime period) {
        super(description, Type.EVENT);
        this.period = period;
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), this.period.format(Parser.formatter));
    }
}
