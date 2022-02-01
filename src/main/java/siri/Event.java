package siri;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Class for the event items of the tasklist, with int done to be indicative of whether the task had been completed, String item to be the name of the task,
 * LocalDate eventDate as the date for the event, and LocalTime eventTime as time for the event.
 */

class Event extends Task {
    
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-LLL-yyyy");
    private LocalDate eventDate;
    private LocalTime eventTime;

    /**
     * Constructor for Event class.
     * 
     * @param item a String to description of the Event task.
     * @param done an int to indicate whether the item is done. 0 indicates not completed and 1 indicates completed.
     * @param eventDate a LocalDate to represent the Event task date.
     * @param eventTime a LocalTime to represent the Event task time.
     */
    public Event(String item, boolean isDone, LocalDate eventDate, LocalTime eventTime) {
        super(item, isDone);
        this.eventDate = eventDate;
        this.eventTime = eventTime;
    }

    /**
     * Compares date passed with the Event task occurance.
     * 
     * @param date the date that the Event task date is comparing to.
     * @return true if date is the same as the Event task date and false if the date is not the same as the Task task date (ignores format).
     */
    public boolean dateCompare(LocalDate date) {
        return this.eventDate.equals(date);
    }


    /**
     * Returns the String consisting details of Event task.
     * 
     * @return String representation of the event task details, including E to represent event task, whether it is done and task name, and date and time of event occurance.
     */
    @Override
    public String getTaskDetails() {
        String taskDetails = "[E]" + super.getTaskDetails() + " (at: " + this.eventDate.format(Event.dtf) + " " + this.eventTime + ")";
        return taskDetails;
    }

    /**
     * Returns the string representation of the data for saving.
     * 
     * @return a string representation of the task for saving.
     */
    @Override
    public String saveData() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
        String dataString = "E " + String.valueOf(this.isDone) + " " + this.item + " /at " + this.eventDate.format(dateFormat) + " " + this.eventTime.format(timeFormat);
        return dataString;
    }
}