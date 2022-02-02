import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    LocalDate date;

    Deadline(String item, LocalDate date) {
        super(item);
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), 
        this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")));
    }
}
