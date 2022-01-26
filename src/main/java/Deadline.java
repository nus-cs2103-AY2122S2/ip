import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{
    static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HHmm");
    String deadlineDate;
    LocalDate deadline;
    LocalTime time;

    public Deadline(String task, String deadlineDate) throws DukeException {
        super(task);
        String[] datetime = deadlineDate.split(" ");
        try {
            deadline = LocalDate.parse(datetime[0], DATE_FORMATTER);
            time = datetime.length == 1 ? null : LocalTime.parse(datetime[1], TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date/time format!" 
                    + " Expected date and/or time in the following formats: \n"
                    + "yyyy-mm-dd | Example: 2022-06-26\n"
                    + "yyyy-mm-dd HHmm | Example: 2022-06-26 2359");
        }
    }

    public Deadline(String task, LocalDate deadline, LocalTime time) {
        super(task);
        this.deadline = deadline;
        this.time = time;
    }

    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        String date = this.deadline.format(dateFormatter);
        String time = this.time == null ? "" : " " + this.time.format(timeFormatter);
        return String.format("[D]%s %s (by: %s%s)", this.statusString(), this.task, date, time);
    }

}
