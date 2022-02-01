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
    
    private LocalDate dueDate;
    private LocalTime dueTime;


    /**
     * Constructor for Deadline class.
     * 
     * @param item a String to description of the Deadline task.
     * @param done an int to indicate whether the item is done. 0 indicates not completed and 1 indicates completed.
     * @param eventDate a LocalDate to represent the Deadline task due date.
     */
    public Deadline(String item, boolean isDone, LocalDate dueDate) {
        super(item, isDone);
        this.dueDate = dueDate;
    }


    /**
     * Constructor for Event class.
     * 
     * @param item a String to description of the Deadline task.
     * @param done an int to indicate whether the item is done. 0 indicates not completed and 1 indicates completed.
     * @param dlDate a LocalDate to represent the Deadline task due date.
     * @param dlTime a LocalTime to represent the Deadline task due time.
     */
    public Deadline(String item, boolean isDone, LocalDate dueDate, LocalTime dueTime) {
        super(item, isDone);
        this.dueDate = dueDate;
        this.dueTime = dueTime;
    }

    /**
     * Compares date passed with the event task occurance.
     * 
     * @param date the date that the Deadline task date is comparing to.
     * @return true if date is the same as the Deadline task date and false if the date is not the same as the Deadline task date (ignores format).
     */
    public boolean dateCompare(LocalDate date) {
        return this.dueDate.equals(date);
    }

    /**
     * Returns the String consisting details of Deadline task.
     * 
     * @return String representation of the event task details, including E to represent event task, whether it is done and task name, and date and time of event occurance.
     */
    @Override
    public String getTaskDetails() {
        String taskDetails;
        if (dueDate == null) {
            taskDetails = "[D]" + super.getTaskDetails() + " (by: " + this.dueDate.format(Deadline.dtf)+ ")";
        } else {
            taskDetails = "[D]" + super.getTaskDetails() + " (by: " + this.dueDate.format(Deadline.dtf) + " " + this.dueTime + ")";
        }

        return taskDetails;
    }

    /**
     * Returns the string representation of the data for saving.
     * 
     * @return a string representation of the task for saving.
     */
    @Override
    public String saveData() {
        String dataString;
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
        if (this.dueTime == null) {
            dataString = "D " + String.valueOf(this.isDone) + " " + this.item + " /by " + this.dueDate.format(dateFormat);
        } else {
            dataString = "D " + String.valueOf(this.isDone) + " " + this.item + " /by " + this.dueDate.format(dateFormat) + " " + this.dueTime.format(timeFormat);
        }
        return dataString;
    }
}