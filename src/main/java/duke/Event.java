package duke;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.zip.DataFormatException;

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
    public Event (String description, String at) throws DukeException {
        super(description);
        DukeException d = new DukeException();

        this.at = at;
        if (at.contains(" ")) {
            String[] dateTimeTemp = at.split(" ");
            this.date = dateTimeTemp[0];
            this.time = dateTimeTemp[1];
        } else {
            assert at.contains("/") : "Input is not a date";
            this.date = at;
            this.time = null;
        }
        DateTimeFormatter.ofPattern("MMM dd yyyy");

    }

    /**
     * @param description
     */
    public Event (String description) {
        super(description);
        this.date = null;
        this.time = null;
    }

    @Override
    public String frontDescription() {
        return "E | " + "[" + this.getStatusIcon() + "] ";
    }
    @Override
    public String backDescription() {
        return super.message();
    }

    @Override
    public String message() {
        if (this.time != null) {
            String messageWithTime = frontDescription()
                    + backDescription() + "(at:" + dateTimeFormat(date) + " " + this.time + ")";
            return messageWithTime;
        } else if (this.date != null) {
            String messageWithoutTime = frontDescription()
                    + backDescription() + "(at:" + dateTimeFormat(date) + ")";
            return messageWithoutTime;
        } else {
            String messageWithoutDateTime = frontDescription()
                    + backDescription();
            return messageWithoutDateTime;
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

    public String dateTimeFormat(String dateTime)  {
        assert at.contains("/") : "Input is not a date";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
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
