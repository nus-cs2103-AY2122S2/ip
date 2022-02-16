package duke;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 Class to represent the Deadline task
 Supports date function and description of task
 */
public class Deadline extends Task {
    protected String deadlineDateTime;
    protected char type;

    /**
     * Constructor for deadline task
     *
     * @param description represents task item
     * @param deadlineDateTime task to be completed by
     *
     */
    public Deadline(String description, String deadlineDateTime) {
        super(description);
        this.deadlineDateTime = deadlineDateTime;
        this.type = 'd';
    }

    /**
     * Getter method to get deadline date from command
     *
     * @return Deadline date of task
     */
    public String getDate() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(deadlineDateTime.substring(3, 13), formatter);
            System.out.println(date.toString());
            DateTimeFormatter newFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
            return date.format(newFormat);
        } catch (DateTimeException e) {
            return "Master, you wished wrongly. Remember you have to wish in this format "
                    + "deadline task /by DD/MM/YYYY TIME. " + "\n" + "Please wish again";
        }
    }

    /**
     * Getter method to get deadline time from command
     *
     * @return Deadline time of task
     */
    public String getTime() {
        return deadlineDateTime.substring(14);
    }

    /**
     *
     * Method to convert task to String type to be printed in the task list
     *
     */
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getDate() + " " + this.getTime() + ")";
    }
}
