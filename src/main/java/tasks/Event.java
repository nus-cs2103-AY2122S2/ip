package tasks;

import duke.DukeException;
import tasks.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Event extends Task {

    protected LocalDate at;
    protected LocalDateTime atTime;
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");


    public Event(String description, String at) throws DukeException {
        super(description);
        if (isDateFormat(at)) {
            this.at = LocalDate.parse(at, dateFormat);
        } else if (isDateTimeFormat(at)) {
            this.atTime = LocalDateTime.parse(at, dateTimeFormat);
        } else {
            throw new DukeException("The date format parsed is incorrect! It should be dd-MM-yyyy or dd-MM-yyyy HH:mm!");
        }
    }

    @Override
    public String toFileFormat() {
        if (at != null) {
            return "E" + super.toFileFormat() + "," + formatDate(at);
        } else {
            return "E" + super.toFileFormat() + "," + formatDateTime(atTime);
        }
    }

    @Override
    public String toString() {
        if (this.at != null) {
            return "[E]" + super.toString() + " (at: " + formatDate(at) + ")";
        } else {
            return "[E]" + super.toString() + " (at: " + formatDateTime(atTime) + ")";
        }
    }

    private boolean isDateTimeFormat(String dateTimeString) {
        try {
            LocalDateTime.parse(dateTimeString, dateTimeFormat);
            return true;
        } catch (DateTimeParseException e) {
            System.out.println("Checking DateTime Format!");
        }
        return false;
    }


    private boolean isDateFormat(String dateString) {
        try {
            LocalDate.parse(dateString, dateFormat);
            return true;
        } catch (DateTimeParseException e) {
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
