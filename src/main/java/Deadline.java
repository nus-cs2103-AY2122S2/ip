import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    public Deadline(String description) throws DukeException {
        super(description);
        try {
            String[] strArr = description.split("/by ");
            String[] DateAndTime = strArr[1].split(" ");
            LocalDate date = LocalDate.parse(DateAndTime[0]);
            this.description = strArr[0] + "(" + "by: " + date.format(DateTimeFormatter.ofPattern("MMM d YYYY")) + ")";;
        }
        catch (ArrayIndexOutOfBoundsException e){
            throw new DukeException();
        }
    }
    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}
