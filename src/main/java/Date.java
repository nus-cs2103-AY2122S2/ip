import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Date {
    LocalDate date;
    String time;

    Date(String time) {
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
            } catch (NumberFormatException e) {
                throw new TesseractException("Please enter date in the YYYY-MM-DD format~");
            }
        }
    }

    boolean equals(Date date1) {
        return this.date.equals(date1.date);
    }

    String formattedTime() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String toString() {
        return this.time;
    }
}
