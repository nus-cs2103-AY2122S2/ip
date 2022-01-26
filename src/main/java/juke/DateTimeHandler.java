package juke;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class DateTimeHandler {
    private LocalDate date;
    private LocalTime time;
    
    public DateTimeHandler(String string) {
        this.parse(string);
    }
    
    private void parse(String string) {
        if (string == null || string.isEmpty()) {
            this.date = null;
            this.time = null;
            return;
        } else {
            DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                    .appendOptional(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                    .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                    .appendOptional(DateTimeFormatter.ofPattern("dd MMM yyyy HHmm")).toFormatter();
            try {
                this.date = LocalDate.parse(string, formatter);
            } catch (DateTimeParseException e) {
                this.date = null;
            }
            try {
                this.time = LocalTime.parse(string, formatter);
                System.out.println("works");
            } catch (DateTimeParseException e) {
                this.time = null;
            }
        }
    }
    
    public String getDateTime() {
        String dt = "";
        if (this.date != null) {
            dt += String.format("%td %tb %tY ", this.date, this.date, this.date);
        }
        if (this.time != null) {
            dt += String.format("%tR", this.time);
        }
        return dt.strip();
    }
}
