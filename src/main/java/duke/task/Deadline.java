package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDate deadlineDate;

    /**
     * Deadline Constructor
     *
     * @param description description of the deadline
     * @param deadlineDate exact deadline date in string format, will be converted into date object
     */
    public Deadline(String description, String deadlineDate) {
        super(description, "D");
        this.deadlineDate = LocalDate.parse(deadlineDate);
    }

    /**
     * Returns deadline date
     *
     * @return LocalDate representing the deadline date
     */
    public LocalDate getDeadlineDate() {
        return this.deadlineDate;
    }

    /**
     * returns deadline in save data format (format to be saved in data.txt file for task storage)
     * deadline date is in the format of "MMM dd yyyy"
     * @return string representing the format it is saved in
     */
    @Override
    public String toSaveDataFormat() {
        String deadlineDateFormatted = this.getDeadlineDate().format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String isDone = (super.checkIsDone() == true) ? "1" : "0";
        return String.format("%s|%s|%s|%s\n", super.getTag(), isDone,
                super.getTaskDescription(), deadlineDateFormatted);
    }

    @Override
    public String toString() {
        String deadlineDateFormatted = this.getDeadlineDate().format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return super.toString() + " (By: " + deadlineDateFormatted + ")";
    }

}
