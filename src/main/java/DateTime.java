import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Handles string conversion to Date and Time objects.
 */
public class DateTime {
    private LocalDate date;
    private LocalTime time;
    private final String str;
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
    DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mma");

    /**
     * Converts input string to date and time objects.
     * @param input Formatted user input as string
     * @throws DateTimeParseException Throws if the date and/or time is not formatted properly
     */
    public DateTime(String input) throws DateTimeParseException {
        // Checks if date is present
        if (input.contains("-")) {
            String[] tempArr = input.split(" ");
            if (tempArr.length == 2) {
                this.date = LocalDate.parse(tempArr[0]);
                this.time = LocalTime.parse(tempArr[1]);
                this.str = date.format(dateFormat) + " " + time.format(timeFormat);
            } else {
                this.date = LocalDate.parse(input);
                this.str = date.format(dateFormat);
            }
        } else {
            this.time = LocalTime.parse(input);
            this.str = time.format(timeFormat);
        }
    }

    public String getString() {
        return this.str;
    }
}
