package duke;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * deadline class
 * inherit from Task class
 */

public class DeadLine extends Task {

    protected String by;
    protected String date;
    protected String time;

    /**
     * @param description
     * @param by
     */
    public DeadLine(String description, String by) {
        super(description);
        this.by = by;
        if (by.contains(" ")) {
            String[] dateTimeTemp = by.split(" ");
            this.date = dateTimeTemp[0];
            this.time = dateTimeTemp[1];
        } else {
            this.date = by;
            this.time = null;
        }
    }

    @Override
    public String frontDescription() {
       return "D | " + "[" + this.getStatusIcon() + "] ";
    }
    @Override
    public String backDescription() {
        return super.message();
    }
    @Override
    public String message() {
        if (this.time != null) {
            return frontDescription() + backDescription()
                    + "(by:" + dateTimeFormat(date) + " " + time + ")";
        } else {
            return frontDescription() + backDescription()
                    + "(by:" + dateTimeFormat(date) + ")";
        }
    }

    @Override
    public String markDone(int currNo) {
        this.isDone = true;
        return this.message();
    }
    /**
     * re-format the date and time
     * @param dateTime
     * @return formated date and time
     */
    public String dateTimeFormat(String dateTime) {
        assert dateTime.contains("/") : "Input is not a date";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate d = LocalDate.parse(dateTime, formatter);
        return d.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Override
    public void updateData (String path) throws IOException {
        FileWriter fw = new FileWriter(path, true);
        fw.write(this.message() + "\n");
        fw.close();
    }
}
