import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    public LocalDate date;
    public LocalTime time;
    public String stringTime;

    public Deadline(String input) {
        super(input.substring(9, input.indexOf("/")));
        this.stringTime = input.substring(input.indexOf("/") + 4);
        String[] tokens = stringTime.split(" ");
        this.date = LocalDate.parse(tokens[0], DateTimeFormatter.ofPattern("d/M/yyyy"));
        this.time = LocalTime.parse(tokens[1], DateTimeFormatter.ofPattern("HHmm"));
    }

    public String getFullDateTime() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + this.time.format(DateTimeFormatter.ofPattern("h.mm a"));
    }

    public LocalDate getDate() {
        return this.date;
    }

    public LocalTime getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + ("(by: " + this.getFullDateTime() + ")");
    }
}
