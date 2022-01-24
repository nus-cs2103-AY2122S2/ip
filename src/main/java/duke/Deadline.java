package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    LocalDate date;
    LocalTime time = null;

    public Deadline(String taskName, String dateTime) throws DukeException {
        super(taskName);
        dateTime = dateTime.trim();
        String[] spl = dateTime.split(" ");
        if(spl.length == 1) {
            try {
                this.date = LocalDate.parse(spl[0]);
            } catch (Exception e) {
                throw new DukeException("Invalid input into date");
            }
        } else if(spl.length == 2) {
            try {
                this.date = LocalDate.parse(spl[0]);
                this.time = LocalTime.parse(spl[1]);
            } catch (Exception e) {
                throw new DukeException("Invalid input into date/time");
            }
        } else {
            throw new DukeException("Please input for a date (optional: time)");
        }
    }

    public void printDate() {
        System.out.print("(by: ");
        System.out.print(this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        printTime();
        System.out.println(")");
    }

    public void printTime() {
        if((this.time != null)){
            System.out.print(" " + this.time.format(DateTimeFormatter.ofPattern(("HH:mm"))));
        }
    }

    @Override
    public void printTask(){
        System.out.print("[D]");

        if(this.getDone()){
            System.out.print("[X] " + this.getTaskName() + " ");
        } else {
            System.out.print("[ ] " + this.getTaskName() + " ");
        }
        printDate();
    }

    @Override
    public String toString(){
        String res = "";
        res += "[D]";
        if(this.getDone()){
            res += "[X]";
        } else {
            res += "[ ]";
        }
        res += " " + this.getTaskName() + " (by: " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        if((this.time != null)){
            res += " " + this.time.format(DateTimeFormatter.ofPattern(("HH:mm")));
        }
        res += ")";
        return res;
    }
}
