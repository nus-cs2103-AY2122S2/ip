package Duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Deadline extends Task {

    /* Variables to store deadline of task */
    protected String by;
    protected LocalTime localTime;
    protected LocalDate atLocalDate;
    protected DateTimeFormatter dTF;

    /**
     * Constructor for Deadline. Parses Deadline from String to LocalDate
     * and LocalTime format.
     *
     * @param description String description of task.
     * @param by String containing deadline of task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        if (this.by.length() > 10) {
            DateTimeFormatter twelveHourFormatter = DateTimeFormatter.ofPattern("h:mma", Locale.ENGLISH);
            String[] dateAndTime = Parser.splitDateAndTime(this.by);
            this.localTime = LocalTime.parse(dateAndTime[1], twelveHourFormatter);
            this.atLocalDate = LocalDate.parse(dateAndTime[0]);
            this.dTF = DateTimeFormatter.ofPattern("dd MMM yyyy");
        } else {
            this.atLocalDate = LocalDate.parse(this.by);
            this.dTF = DateTimeFormatter.ofPattern("dd MMM yyyy");
        }
    }

    /**
     * Checks if Deadline has a LocalTime.
     *
     * @return True if Deadline has LocalTime, else false.
     */
    public boolean hasTime() {
       return this.localTime != null;
    }

    /**
     * Returns a description of task with deadline
     *
     * @return Description of task with status icon, date and time.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dTF.format(atLocalDate) + (hasTime() ? ", " + localTime.toString() + ")" : ")");
    }
}
