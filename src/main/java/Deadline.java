import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected LocalDate date;
    protected String time;
    private final DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MMM dd yyyy");

    public Deadline(String description, String date) {
        super(description);
        time = "";
        String[] dateSplit = date.split(" ", 2);
        try {
            this.date = LocalDate.parse(dateSplit[0]);
            if (dateSplit.length != 1) {
                time = dateSplit[1];
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date! Date format to be of type yyyy-mm-dd\n");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(pattern) + " " + time +")";
    }

    public String toSave() {
        return String.format("D | %d | %s | %s", this.isDone ? 1 : 0, this.description, this.by);
    }
}
