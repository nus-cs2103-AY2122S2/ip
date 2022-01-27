import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDate date;

    public Deadline(String details, LocalDate date) {
        super(details);
        this.date = date;
    }

    @Override
    public String toString() {
        return "D" + super.toString() + " | " + this.date.format(DateTimeFormatter.ofPattern("MMM dd, yyyy"));

    }

}
