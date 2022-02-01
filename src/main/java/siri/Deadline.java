package siri;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

class Deadline extends Task {
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-LLL-yyyy");
    
    private LocalDate dueDate;
    private LocalTime dueTime;

    /**
        Constructor for Deadline class.

        @param item a String to description of the Deadline task.
        @param done an int to indicate whether the item is done. 0 indicates not completed and 1 indicates completed.
        @param dlDate a LocalDate item to store the due date.
     */
    public Deadline(String item, int done, LocalDate dueDate) {
        super(item, done);
        this.dueDate = dueDate;
    }


    /**
        Constructor for Deadline class.

        @param item a String to description of the Deadline task.
        @param done an int to indicate whether the item is done. 0 indicates not completed and 1 indicates completed.
        @param dlDate a LocalDate to store the due date.
        @param dlTime a LocalTime to store the due time.
     */
    public Deadline(String item, int done, LocalDate dueDate, LocalTime dueTime) {
        super(item, done);
        this.dueDate = dueDate;
        this.dueTime = dueTime;
    }

    public boolean dateCompare(LocalDate date) {
        return this.dueDate.equals(date);
    }

    /**
        Method to display the item name and status in listing standard.

        @return String showing the task category, status and name.
    */
    @Override
    public String getTaskDetails() {
        String returned;
        if (dueDate == null) {
            returned = "[D]" + super.getTaskDetails() + " (by: " + this.dueDate.format(Deadline.dtf)+ ")";
        } else {
            returned = "[D]" + super.getTaskDetails() + " (by: " + this.dueDate.format(Deadline.dtf) + " " + this.dueTime + ")";
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
        if (this.dueTime == null) {
            returned = "D " + this.done + " " + this.item + " /by " + this.dueDate.format(dateFormat);
        } else {
            returned = "D " + this.done + " " + this.item + " /by " + this.dueDate.format(dateFormat) + " " + this.dueTime.format(timeFormat);
        }
        return returned;
    }
}