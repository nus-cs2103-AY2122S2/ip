import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Time {

    public final int label;

    private Time(int label) {
        this.label = label;
    }

    public static String convertToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
    }

    public static LocalDate convertToDate(String date) {
        String[] dates = date.split(" ");
        int year = Integer.parseInt(dates[2]);
        int day = Integer.parseInt(dates[0]);
        if (dates[1].equals("Jan")) {
            return LocalDate.of(year, 1, day);
        } else if (dates[1].equals("Feb")) {
            return LocalDate.of(year, 2, day);
        } else if (dates[1].equals("Mar")) {
            return LocalDate.of(year, 3, day);
        } else if (dates[1].equals("Apr")) {
            return LocalDate.of(year, 4, day);
        } else if (dates[1].equals("May")) {
            return LocalDate.of(year, 5, day);
        } else if (dates[1].equals("Jun")) {
            return LocalDate.of(year, 6, day);
        } else if (dates[1].equals("July")) {
            return LocalDate.of(year, 7, day);
        } else if (dates[1].equals("Aug")) {
            return LocalDate.of(year, 8, day);
        } else if (dates[1].equals("Sep")) {
            return LocalDate.of(year, 9, day);
        } else if (dates[1].equals("Oct")) {
            return LocalDate.of(year, 10, day);
        } else if (dates[1].equals("Nov")) {
            return LocalDate.of(year, 11, day);
        } else {
            return LocalDate.of(year, 12, day);
        }
    }
}
