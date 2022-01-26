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
    public Deadline(String description, String deadlineDate){
        super(description, "D");
        this.deadlineDate = LocalDate.parse(deadlineDate);
    }

    /**
     * Returns deadline date in the format of "MMM dd yyyy"
     *
     * @return string representing deadline date in "MMM dd yyyy"
     */
    public String getDeadlineDate(){
        return this.deadlineDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    /**
     * returns deadline in save data format (format to be saved in data.txt file for task storage)
     * @return string representing the format it is saved in
     */
    @Override
    public String toSaveDataFormat() {
        String isDone = (super.checkIsDone() == true) ? "1" : "0";
        return String.format("%s|%s|%s|%s\n", super.getTag(), isDone, super.getTaskDescription(), this.getDeadlineDate());
    }

    @Override
    public String toString() {
        return super.toString() + " (By: " + this.getDeadlineDate() + ")";
    }

}
