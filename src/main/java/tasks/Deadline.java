package tasks;
import duke.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;
    protected LocalDateTime byTime;
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public Deadline(String description, String by) throws DukeException {
        super(description);
        if (isDateFormat(by)) {
            this.by = LocalDate.parse(by, dateFormat);
        } else if (isDateTimeFormat(by)) {
            this.byTime = LocalDateTime.parse(by, dateTimeFormat);
        } else {
            throw new DukeException("The date format parsed is incorrect!" +
                    "It should be dd-MM-yyyy or dd-MM-yyyy HH:mm!");
        }
    }

    @Override
    public String toFileFormat() {
        if (by != null) {
            return "D" + super.toFileFormat() + "," + formatDate(by);
        } else {
            return "D" + super.toFileFormat() + "," + formatDateTime(byTime);
        }
    }

    @Override
    public String toString() {
        if (this.by != null) {
            return "[D]" + super.toString() + " (by: " + formatDate(by) + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + formatDateTime(byTime) + ")";
        }
    }

    private boolean isDateTimeFormat(String dateTimeString) {
        try {
            LocalDateTime.parse(dateTimeString, dateTimeFormat);
            return true;
        } catch (Exception e) {
            System.out.println("Checking DateTime Format!");
        }
        return false;
    }

    private boolean isDateFormat(String dateString)  {
        try {
            LocalDate.parse(dateString, dateFormat);
            return true;
        } catch (Exception e) {
            System.out.println("Checking Date Format!");
        }
        return false;
    }

    public String formatDateTime(LocalDateTime dateTime) {
        String day = dateTime.getDayOfMonth() < 10
                ? "0" + dateTime.getDayOfMonth()
                : "" + dateTime.getDayOfMonth();
        String month = dateTime.getMonthValue() < 10
                ? "0" + dateTime.getMonthValue()
                : "" + dateTime.getMonthValue();
        Integer year = dateTime.getYear();
        String hour = dateTime.getHour() < 10
                ? "0" + dateTime.getHour()
                : "" + dateTime.getHour();
        String minute = dateTime.getMinute() < 10
                ? "0" + dateTime.getMinute()
                : "" +  dateTime.getMinute();
        return day + "-" + month + "-" + year + " " + hour + ":" + minute;
    }

    public String formatDate(LocalDate date) {
        String day = date.getDayOfMonth() < 10
                ? "0" + date.getDayOfMonth()
                : "" + date.getDayOfMonth();
        String month = date.getMonthValue() < 10
                ? "0" + date.getMonthValue()
                : "" + date.getMonthValue();
        Integer year = date.getYear();
        return day + "-" + month + "-" + year;
    }

}

