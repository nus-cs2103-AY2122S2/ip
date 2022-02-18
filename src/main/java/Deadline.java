import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate date;
    protected LocalDateTime dateTime;
    protected String deadline;

    public Deadline(String description, String initialLetter, String deadline) {
        super(description, initialLetter);
        this.deadline = deadline;
        //find a way to scan deadline after description entered by user, maybe use the "/" as delimiter for the scanner?
    }

    //reused from Brigette Santoso E0564307 Level-8 source code
    public Deadline(String description, String initialLetter, LocalDate date) {
        super(description, initialLetter);
        this.deadline = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        this.date = date;
    }

    //reused from Brigette Santoso E0564307 Level-8 source code
    public Deadline(String description, String initialLetter, LocalDateTime dateTime) {
        super(description, initialLetter);
        this.deadline = dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm:ss"));
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "[D]" + "[" + this.getStatusIcon() + "] " + this.description + " (by: " + this.deadline + ")";
    }

}
