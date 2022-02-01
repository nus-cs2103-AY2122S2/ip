package bobby;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructor to create an instance of Deadline.
     * @param description description of the Deadline Task to be done.
     * @param by deadline in which task needs to be done in YYYY-MM-DD format.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public void writeToFile(FileWriter fw) throws IOException {
        fw.write("D ; " + isDone + " ; " + description + " ; " + by + System.lineSeparator());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}