package apollo.tasks;

import apollo.parser.Parser;

import java.time.LocalDateTime;

public class Deadline extends Task {

    private final LocalDateTime time;

    public Deadline(String description, LocalDateTime time) {
        super(description, Type.DEADLINE);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)",
                super.toString(),
                this.time.format(Parser.formatter));
    }
}
