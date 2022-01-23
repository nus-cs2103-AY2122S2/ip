import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.time.temporal.ChronoUnit.DAYS;

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
            super.saveFormat = "E," + this.description + "," + date + "," + time + "," + super.isDone;
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println("yo");
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
            super.saveFormat = saveFormat;
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
