package bobby;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate date;

    /**
     * Constructor to create an instance of Deadline.
     *
     * @param description description of the Deadline Task to be done.
     * @param date deadline in which task needs to be done in YYYY-MM-DD format.
     */
    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    @Override
    public void writeToFile(FileWriter fw) throws IOException {
        fw.write("D ; " + isDone + " ; " + description + " ; " + date + System.lineSeparator());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}