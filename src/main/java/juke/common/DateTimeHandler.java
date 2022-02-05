package juke.common;

import juke.exception.JukeParseException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

public class DateTimeHandler {
    private LocalDateTime dateTime;
    
    public DateTimeHandler(String string) throws JukeParseException {
        this.parse(string);
    }
    
    private void parse(String string) throws JukeParseException {
        if (string == null || string.isEmpty()) {
            throw new JukeParseException("date and time");
        } else {
            DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                    .appendOptional(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                    .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"))
                    .appendOptional(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")).toFormatter();
            try {
                this.dateTime = LocalDateTime.parse(string, formatter);
            } catch (DateTimeParseException e) {
                throw new JukeParseException("date and time");
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
