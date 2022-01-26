package juke;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class DateTimeHandler {
    private LocalDateTime dateTime;
    
    public DateTimeHandler(String string) {
        this.parse(string);
    }
    
    private void parse(String string) {
        if (string == null || string.isEmpty()) {
            this.dateTime = null;
            return;
        } else {
            DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                    .appendOptional(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                    .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"))
                    .appendOptional(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")).toFormatter();
            try {
                this.dateTime = LocalDateTime.parse(string, formatter);
            } catch (DateTimeParseException e) {
                this.dateTime = null;
            }
        }
    }
    
    public String getDateTime() {
        String dt = "";
        if (this.dateTime != null) {
            dt += String.format("%td %tb %tY %tR", this.dateTime, this.dateTime, this.dateTime, this.dateTime);
        }
        return dt;
    }
}
