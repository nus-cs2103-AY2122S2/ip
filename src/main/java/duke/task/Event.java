package duke.task;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;

public class Event extends Task{
    static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HHmm");
    LocalDate startDate;
    LocalTime time;

    public Event (String task, String startDateString) throws DukeException {
        super(task);
        String[] datetime = startDateString.split(" ");
        
        try {
            startDate = LocalDate.parse(datetime[0], DATE_FORMATTER);
            time = datetime.length == 1 ? null : LocalTime.parse(datetime[1], TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            System.out.println(e);
            throw new DukeException("Invalid date/time format!" 
                    + " Expected date and/or time in the following formats: \n"
                    + "yyyy-mm-dd | Example: 2022-06-26\n"
                    + "yyyy-mm-dd HHmm | Example: 2022-06-26 2359");
        }
    }

    public Event(String task, LocalDate startDate, LocalTime time) {
        super(task);
        this.startDate = startDate;
        this.time = time;
    }

    public Event (String task, boolean isDone, String startDate) throws DukeException{
        this(task, startDate);
        this.isDone = isDone;
    }

    public String toFileFormat() {
        Integer i = this.isDone ? 1 : 0;
        String startDateString = startDate.format(DATE_FORMATTER);
        String timeString = time == null ? "" : " " + time.format(TIME_FORMATTER);
        return String.format("E | %d | %s | %s%s\n", 
                i, this.task, startDateString, timeString);
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        String date = this.startDate.format(dateFormatter);
        String time = this.time == null ? "" : " " + this.time.format(timeFormatter);
        return String.format("[E]%s %s (at: %s%s)", this.statusString(), this.task, date, time);
    }
}
