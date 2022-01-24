package tasks;

import duke.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    String[] DateAndTime;
    LocalDate date;
    String time;

    public Deadline(String description) throws DukeException {
        super(description);
        try {
            String[] strArr = description.split("/by ");
            this.description = strArr[0];
            DateAndTime = strArr[1].split(" ");
            date = LocalDate.parse(DateAndTime[0]);
            time = DateAndTime[1];
            super.saveFormat = "D," + this.description + "," + date + "," + time + "," + super.isDone;
        }
        catch (ArrayIndexOutOfBoundsException e){
            throw new DukeException();
        }
    }
    public Deadline(String saveFormat, boolean blean) throws DukeException {
        super(saveFormat);
        try {
            String[] strArr = description.split(",");
            this.description = strArr[1];
            date = LocalDate.parse(strArr[2]);
            time = strArr[3];
            if (Boolean.parseBoolean(strArr[4])){
                super.setDone();
            }
            super.saveFormat = strArr[0] + "," + strArr[1] + "," + strArr[2] + "," + strArr[3];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException();
        }
    }

    @Override
    public String toString() {
        return  "D | " + super.toString() + "BY: " + date.format(DateTimeFormatter.ofPattern("MMM d YYYY")) +
                " " + time;
    }
}
