import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;

/**
 * DateParse class parse a varying format of dates into a LocalDate object.
 */
public class DateParse {

    private LocalDate date;
    private boolean isFound = false;

    // All the available formats Jeff accepts as dates
    ArrayList<DateTimeFormatter> knownPatterns = new ArrayList<DateTimeFormatter>();
    DateTimeFormatter format1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter format2 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    DateTimeFormatter format3 = DateTimeFormatter.ofPattern("yyyy-MM-d");
    DateTimeFormatter format4 = DateTimeFormatter.ofPattern("yyyy/MM/d");
    DateTimeFormatter format5 = DateTimeFormatter.ofPattern("yyyy-M-d");
    DateTimeFormatter format6 = DateTimeFormatter.ofPattern("yyyy/M/d");
    DateTimeFormatter format7 = DateTimeFormatter.ofPattern("yyyy-M-dd");
    DateTimeFormatter format8 = DateTimeFormatter.ofPattern("yyyy/M/dd");
    DateTimeFormatter format9 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    DateTimeFormatter format10 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter format11 = DateTimeFormatter.ofPattern("d-MM-yyyy");
    DateTimeFormatter format12 = DateTimeFormatter.ofPattern("d/MM/yyyy");
    DateTimeFormatter format13 = DateTimeFormatter.ofPattern("d-M-yyyy");
    DateTimeFormatter format14 = DateTimeFormatter.ofPattern("d/M/yyyy");
    DateTimeFormatter format15 = DateTimeFormatter.ofPattern("dd-M-yyyy");
    DateTimeFormatter format16 = DateTimeFormatter.ofPattern("dd/M/yyyy");

    /**
     * Constructor for DateParse
     *
     * @param str String to be parsed into a LocalDate object
     * @throws JeffException
     */
    public DateParse(String str) throws JeffException {
        knownPatterns.add(format1);
        knownPatterns.add(format2);
        knownPatterns.add(format3);
        knownPatterns.add(format4);
        knownPatterns.add(format5);
        knownPatterns.add(format6);
        knownPatterns.add(format7);
        knownPatterns.add(format8);
        knownPatterns.add(format9);
        knownPatterns.add(format10);
        knownPatterns.add(format11);
        knownPatterns.add(format12);
        knownPatterns.add(format13);
        knownPatterns.add(format14);
        knownPatterns.add(format15);
        knownPatterns.add(format16);

        int curr = 0;
        while((!isFound) && curr < 16) {
            try {
                date = LocalDate.parse(str, knownPatterns.get(curr));
                isFound = true;
            } catch (DateTimeParseException e) {
                curr++;
            }
        }

        // When the input string cannot be parsed, return JeffException
        if ((!isFound) && curr == 16) {
            throw new JeffException("Sorry but Jeff does not understand the date format given\n"
                    + "Please check the readme.txt for the acceptable formats.");
        }
//        this.helper();
    }

//    private LocalDate helper() {
//        return date;
//    }

    public String toString() {
        return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
}
