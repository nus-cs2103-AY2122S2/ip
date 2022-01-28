import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Deadline extends Task {

    private final LocalDate by;

    public Deadline(String name, String by) {
        super(name);
        by = by.trim();
        this.by = LocalDate.parse(by);
    }

    /**
     * used to retrieve data from hard drive
     * @param name
     * @param by
     * @param done
     */
    public Deadline(String name, LocalDate by, boolean done) {
        super(name, done);
        this.by = by;
    }

    public LocalDate getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
}