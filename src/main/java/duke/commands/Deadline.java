package duke.commands;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    private String by;
    private LocalDate date;
    private LocalTime time;
    private String dateFormat;
    private String timeFormat;

    private static final String[] DATE_FORMATS = {
            "dd/MM/yyyy",
            "dd MM yyyy",
            "yyyy/MM/dd",
            "yyyy MM dd",
            "MMM dd yyyy",
    };
    private static final String[] TIME_FORMATS = {
            "HHmm",
            "HH:mm",
    };

    public Deadline(String description, String by) {
        super(description, "D");
        this.by = by;
    }

    public boolean isValidTime() {
        for (String format : TIME_FORMATS) {
            try {
                if (this.by.split(" ").length != 1) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                    LocalTime.parse(this.by.split(" ")[1], formatter);
                    this.timeFormat = format;
                    return true;
                } else {
                    return false;
                }
            } catch (DateTimeParseException e) {
            }
        }
        return false;
    }

    public boolean isValidDate() {
        String d = this.by.split(" ")[0].replace("-", " ");
        for (String format : DATE_FORMATS) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                LocalDate.parse(d, formatter);
                this.dateFormat = format;
                return true;
            } catch (DateTimeParseException e) {
            }
        }
        return false;
    }
    @Override
    public String getDate() {
        String d = this.by.split(" ")[0].replace("-", " ");
        if (this.isValidDate()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(this.dateFormat);
            this.date = LocalDate.parse(d, formatter);
            return this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } else {
            return "";
        }
    }
    @Override
    public String getTime() {
        if (this.isValidTime()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(this.timeFormat);
            this.time = LocalTime.parse(this.by.split(" ")[1], formatter);
            return this.time.format(DateTimeFormatter.ofPattern("HH:mm a"));
        } else {
            return "";
        }
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + (this.getDate() + " " + this.getTime()).trim() + ")";
    }
}
