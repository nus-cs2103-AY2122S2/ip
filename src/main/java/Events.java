import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Events extends Task {
    protected static String type = "E";
    protected String printed;
    protected LocalDate date;
    protected LocalTime time;

    public Events(String description, boolean isDone) throws DukeException {
        super(type, description, isDone);
        try {
            String[] temp = description.split("/at ");
            if (temp.length > 1) {
                String[] temp2 = temp[1].split(" ");

                if (temp2.length > 1) {
                    this.date = LocalDate.parse(temp2[0]);
                    this.time = LocalTime.parse(temp2[1]);

                    this.printed = temp[0] + " (by: "
                            + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " "
                            + this.time.format(DateTimeFormatter.ofPattern("hh:mma")) + ")";
                } else {
                    throw new DukeException(
                            "Please include the time in the deadline in the following manner: yyyy-mm-dd hh:mm");
                }
            } else {
                throw new DukeException("Please input a deadline in the following manner: yyyy-mm-dd hh:mm");
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Please input the deadline in the proper manner: yyyy-mm-dd hh:mm");
        }
    }

    public Events(String description) throws DukeException {
        this(description, false);
    }

    @Override
    public String toString() {
        return this.isDone ? "[E][X] " + this.printed
                : "[E][ ] " + this.printed;
    }

}
