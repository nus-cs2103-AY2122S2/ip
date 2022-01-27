package tesseract.main;

import tesseract.main.TesseractException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.DateTimeException;


public class Date {
    LocalDate date;
    String time;

    public Date(String time) {
        this.time = time;
        this.date = LocalDate.parse(time);
    }

    public static void checkValidTime(String time) throws TesseractException {
        if (time.length() != 10) { // check if the string is of length 10
            throw new TesseractException("Please enter date in the YYYY-MM-DD format~");
        } else {
            try {
                String[] ints = time.split("-", 3);
                for (String integer : ints) {
                    Integer.parseInt(integer);
                }
                new Date(time).formattedTime();
            } catch (DateTimeException e) {
                throw new TesseractException("Please enter a valid date that can be found on calender :P");
            } catch (NumberFormatException e) {
                throw new TesseractException("Please enter date in the YYYY-MM-DD format~");
            }
        }
    }

    public boolean equals(Date date1) {
        return this.date.equals(date1.date);
    }

    public String formattedTime() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String toString() {
        return this.time;
    }
}
