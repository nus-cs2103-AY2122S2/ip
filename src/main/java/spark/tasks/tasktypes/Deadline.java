package spark.tasks.tasktypes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime deadlineDateTime;
    private static final DateTimeFormatter inputDateTimeFormatter = DateTimeFormatter.ofPattern("M-d-yyyy Hmm");
    private static final DateTimeFormatter outputDateTimeFormatter = DateTimeFormatter.ofPattern("d MMM yyyy, h:mm a");

    /**
     * Creates a new Deadline.
     *
     * @param name
     * @param by
     */
    public Deadline (String name, String by) {
        super(name);
        this.deadlineDateTime = LocalDateTime.parse(by, inputDateTimeFormatter);
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
        this.deadlineDateTime = LocalDateTime.parse(by, inputDateTimeFormatter);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(),
                this.deadlineDateTime.format(outputDateTimeFormatter));
    }

    @Override
    public String encodeTask() {
        return String.format("D @@@ %b @@@ %s @@@ %s",
                super.isDone(),
                super.getTitle(),
                this.deadlineDateTime.format(inputDateTimeFormatter));
    }
}
