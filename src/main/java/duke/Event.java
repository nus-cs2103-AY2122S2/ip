package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    LocalDate date;
    LocalTime time = null;

    //date is entered as "__date__ __time__"
    public Event(String taskName, String dateTime) throws DukeException {
        super(taskName);

        dateTime = dateTime.trim();
        String[] splitString = dateTime.split(" ");
        if (splitString.length == 1) {
            try {
                this.date = LocalDate.parse(splitString[0]);
            } catch (Exception e) {
                throw new DukeException("Invalid input into date");
            }
        } else if (splitString.length == 2) {
            try {
                this.date = LocalDate.parse(splitString[0]);
                this.time = LocalTime.parse(splitString[1]);
            } catch (Exception e) {
                throw new DukeException("Invalid input into date/time");
            }
        } else {
            throw new DukeException("Please input for a date (optional: time)");
        }
    }

    public void printDate() {
        System.out.print("(at: ");
        System.out.print(this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        printTime();
        System.out.println(")");
    }

    public void printTime() {
        if (this.time != null) {
            System.out.print(" " + this.time.format(DateTimeFormatter.ofPattern(("HH:mm"))));
        }
    }

    @Override
    public void printTask(){
        //print task type
        System.out.print("[E]");
        //print task done symbol
        if (this.done){
            System.out.print("[X] " + this.taskName + " ");
        } else {
            System.out.print("[ ] " + this.taskName + " ");
        }
        printDate();
    }

    @Override
    public String toString(){
        String result = "";
        result += "[E]";
        if (this.done){
            result += "[X]";
        } else {
            result += "[ ]";
        }
        result += this.taskName + " (at: " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        if (this.time != null) {
            result += " " + this.time.format(DateTimeFormatter.ofPattern(("HH:mm")));
        }
        result += ")";
        return result;
    }
}
