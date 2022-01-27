import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected LocalDate date;
    protected String time;
    private final DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MMM dd yyyy");

    public Event(String description, String date) {
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
        return "[E]" + super.toString() + " (at: " + date.format(pattern) + " " + time +  ")";
    }
}
