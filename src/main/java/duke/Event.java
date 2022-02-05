package duke;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * event class
 * inherit from Task class
 */
public class Event extends Task {
    protected String at;
    protected String date;
    protected String time;

    /**
     * @param description
     * @param at
     */
    public Event (String description, String at) {
        super(description);
        this.at = at;
        if (at.contains(" ")) {
            String[] dateTimeTemp = at.split(" ");
            this.date = dateTimeTemp[0];
            this.time = dateTimeTemp[1];
        } else {
            this.date = at;
            this.time = null;
        }
    }

    @Override
    public String message() {
        if (this.time != null) {
            return "E | " + "[" + this.getStatusIcon() + "] "
                    + super.message() + "(at:" + dateTimeFormat(date) + " " + this.time + ")";
        } else {
            return "E | " + "[" + this.getStatusIcon() + "] "
                    + super.message() + "(at:" + dateTimeFormat(date) + ")";
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
     * @return reformatted date and time
     */
    public String dateTimeFormat(String dateTime) {
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
