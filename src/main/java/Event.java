import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.time.temporal.ChronoUnit.DAYS;

public class Event extends Task{

    public Event(String description) throws DukeException {
        super(description);
        try {
            String[] strArr = description.split("/at ");
            String[] DateAndTime = strArr[1].split(" ");
            LocalDate date = LocalDate.parse(DateAndTime[0]);
            if (DateAndTime[1] != null) {
                this.description = strArr[0] + "| " + "AT: " + date.format(DateTimeFormatter.ofPattern("MMM d YYYY")) +
                        " " + DateAndTime[1];
            }
            else{
                this.description = strArr[0] + "| " + "AT: " + date.format(DateTimeFormatter.ofPattern("MMM d YYYY"));
            }
        }
        catch (ArrayIndexOutOfBoundsException e){
            throw new DukeException();
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }

}
