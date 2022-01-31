package siri;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

class Deadline extends Task {
    private String dateTime;


    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-LLL-yyyy");
    private LocalDate dlDate;
    private LocalTime dlTime;

    /**
        Constructor for Deadline class.

        @param item a String to description of the Deadline task.
        @param done an int to indicate whether the item is done. 0 indicates not completed and 1 indicates completed.
        @param dlDate a LocalDate item to store the due date.
     */
    public Deadline(String item, int done, LocalDate dlDate) {
        super(item, done);
        this.dlDate = dlDate;
    }


    /**
        Constructor for Deadline class.

        @param item a String to description of the Deadline task.
        @param done an int to indicate whether the item is done. 0 indicates not completed and 1 indicates completed.
        @param dlDate a LocalDate to store the due date.
        @param dlTime a LocalTime to store the due time.
     */
    public Deadline(String item, int done, LocalDate dlDate, LocalTime dlTime) {
        super(item, done);
        this.dlDate = dlDate;
        this.dlTime = dlTime;
    }

    public boolean dateCompare(LocalDate date) {
        return this.dlDate.equals(date);
    }

    /**
        Method to display the item name and status in listing standard.

        @return String showing the task category, status and name.
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
        Method to produce String for saving purposes.

        @return String that consist of the details of the Deadline task details.
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