package spark.tasks.tasktypes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime by;
    private static final DateTimeFormatter inputDateTimeFormatter = DateTimeFormatter.ofPattern("M-d-yyyy Hmm");
    private static final DateTimeFormatter outputDateTimeFormatter = DateTimeFormatter.ofPattern("d MMM yyyy, h:mm a");

    /**
     * Creates a new Deadline.
     *
     * @param name
     * @param by
     */
    public Deadline (String name, LocalDateTime by) {
        super(name);
        this.by = by;
    }

    /**
     * Generates a new Deadline and initialises it with the
     * given completion status.
     *
     * @param isDone
     * @param title
     * @param by
     */
    public Deadline (boolean isDone, String title, String by) {
        super(isDone, title);
        this.by = LocalDateTime.parse(by, inputDateTimeFormatter);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(),
                this.by.format(outputDateTimeFormatter));
    }

    @Override
    public String encodeTask() {
        return String.format("D @@@ %b @@@ %s @@@ %s",
                super.isDone(),
                super.getTitle(),
                this.by.format(inputDateTimeFormatter));
    }
}
