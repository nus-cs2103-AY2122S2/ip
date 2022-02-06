import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDate time;

    public Deadline(String content, LocalDate time) {
        super(content);
        this.time = time;
    }

    public Deadline(String content, LocalDate time, boolean isDone) {
        super(content, isDone);
        this.time = time;
    }

    @Override
    public Deadline mark(boolean isDone) {
        return new Deadline(super.getContent(), time, isDone);
    }

    @Override
    public String fileFormat() {
        return "D" + super.fileFormat() + time + " | ";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + time.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }
}
