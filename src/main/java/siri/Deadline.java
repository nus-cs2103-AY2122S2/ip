package siri;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Class for the deadline items of the tasklist, with int done to be indicative of whether the task had been completed, String item to be the name of the task,
 * LocalDate eventDate as the due date for the deadline task (and LocalTime eventTime as due time for the deadline task).
 */

class Deadline extends Task {

    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-LLL-yyyy");
    private LocalDate dlDate;
    private LocalTime dlTime;


    /**
     * Constructor for Deadline class.
     * 
     * @param item a String to description of the Deadline task.
     * @param done an int to indicate whether the item is done. 0 indicates not completed and 1 indicates completed.
     * @param eventDate a LocalDate to represent the Deadline task due date.
     */
    public Deadline(String item, int done, LocalDate dlDate) {
        super(item, done);
        this.dlDate = dlDate;
    }


    /**
     * Constructor for Event class.
     * 
     * @param item a String to description of the Deadline task.
     * @param done an int to indicate whether the item is done. 0 indicates not completed and 1 indicates completed.
     * @param dlDate a LocalDate to represent the Deadline task due date.
     * @param dlTime a LocalTime to represent the Deadline task due time.
     */
    public Deadline(String item, int done, LocalDate dlDate, LocalTime dlTime) {
        super(item, done);
        this.dlDate = dlDate;
        this.dlTime = dlTime;
    }

    /**
     * Method for comparing date with the event task occurance.
     * 
     * @param date the date that the Deadline task date is comparing to.
     * @return true if date is the same as the Deadline task date and false if the date is not the same as the Deadline task date (ignores format).
     */
    public boolean dateCompare(LocalDate date) {
        return this.dlDate.equals(date);
    }

    /**
     * Method to get String of task status and task name.
     * 
     * @return String representation of the event task details, including E to represent event task, whether it is done and task name, and date and time of event occurance.
     */
    @Override
    public String getItemAndStatus() {
        String returned;
        if (dlTime == null) {
            returned = "[D]" + super.getItemAndStatus() + " (by: " + this.dlDate.format(Deadline.dtf)+ ")";
        } else {
            returned = "[D]" + super.getItemAndStatus() + " (by: " + this.dlDate.format(Deadline.dtf) + " " + this.dlTime + ")";
        }

        return returned;
    }

    /**
     * Method to return the string representation of the data for saving.
     * 
     * @return a string representation of the task for saving.
     */
    @Override
    public String saveData() {
        String returned;
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
        if (this.dlTime == null) {
            returned = "D " + this.done + " " + this.item + " /by " + this.dlDate.format(dateFormat);
        } else {
            returned = "D " + this.done + " " + this.item + " /by " + this.dlDate.format(dateFormat) + " " + this.dlTime.format(timeFormat);
        }
        return returned;
    }
}