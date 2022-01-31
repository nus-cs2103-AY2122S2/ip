package siri;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

class Event extends Task {
    
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-LLL-yyyy");
    private LocalDate eventDate;
    private LocalTime eventTime;

    /**
        Constructor for Event class.

        @param item a String to description of the Event task.
        @param done an int to indicate whether the item is done. 0 indicates not completed and 1 indicates completed.
        @param eventDate a LocalDate to describe the Event date.
        @param eventTime a LocalTime to describe the Event time.
     */
    public Event(String item, int done, LocalDate eventDate, LocalTime eventTime) {
        super(item, done);
        this.eventDate = eventDate;
        this.eventTime = eventTime;
    }

    public boolean dateCompare(LocalDate date) {
        return this.eventDate.equals(date);
    }


    /**
        Method to display the item name and status in listing standard.

        @return String showing the task category, status and name.
    */
    @Override
    public String getItemAndStatus() {
        String returned = "[E]" + super.getItemAndStatus() + " (at: " + this.eventDate.format(Event.dtf) + " " + this.eventTime + ")";
        return returned;
    }

    /**
        Method to produce String for saving purposes.

        @return String that consist of the details of the Event task details.
     */
    @Override
    public String saveData() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
        String returned = "E " + this.done + " " + this.item + " /at " + this.eventDate.format(dateFormat) + " " + this.eventTime.format(timeFormat);
        return returned;
    }
}