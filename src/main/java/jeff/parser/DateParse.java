package jeff.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import jeff.main.JeffException;

/**
 * DateParse class parse a varying format of dates into a LocalDate object.
 */
public class DateParse {

    private LocalDate date;
    private boolean isFound = false;

    // All the available formats Jeff accepts as dates
    private DateTimeFormatter format1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private DateTimeFormatter format2 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private DateTimeFormatter format3 = DateTimeFormatter.ofPattern("yyyy-MM-d");
    private DateTimeFormatter format4 = DateTimeFormatter.ofPattern("yyyy/MM/d");
    private DateTimeFormatter format5 = DateTimeFormatter.ofPattern("yyyy-M-d");
    private DateTimeFormatter format6 = DateTimeFormatter.ofPattern("yyyy/M/d");
    private DateTimeFormatter format7 = DateTimeFormatter.ofPattern("yyyy-M-dd");
    private DateTimeFormatter format8 = DateTimeFormatter.ofPattern("yyyy/M/dd");
    private DateTimeFormatter format9 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private DateTimeFormatter format10 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private DateTimeFormatter format11 = DateTimeFormatter.ofPattern("d-MM-yyyy");
    private DateTimeFormatter format12 = DateTimeFormatter.ofPattern("d/MM/yyyy");
    private DateTimeFormatter format13 = DateTimeFormatter.ofPattern("d-M-yyyy");
    private DateTimeFormatter format14 = DateTimeFormatter.ofPattern("d/M/yyyy");
    private DateTimeFormatter format15 = DateTimeFormatter.ofPattern("dd-M-yyyy");
    private DateTimeFormatter format16 = DateTimeFormatter.ofPattern("dd/M/yyyy");
    private ArrayList<DateTimeFormatter> knownPatterns = new ArrayList<DateTimeFormatter>();

    /**
     * Constructor for DateParse
     *
     * @param str String to be parsed into a LocalDate object
     * @throws JeffException When no available format is available to parse date input.
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
        while ((!isFound) && curr < 16) {
            try {
                date = LocalDate.parse(str, knownPatterns.get(curr));
                isFound = true;
            } catch (DateTimeParseException e) {
                curr++;
            }
        }

        if ((!isFound) && curr == 16) {
            throw new JeffException("Sorry but Jeff does not understand the date format given\n"
                    + "Please check the readme.txt for the acceptable formats.");
        }
    }

    public String toString() {
        return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
}
