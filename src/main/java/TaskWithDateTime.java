import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.temporal.ChronoUnit;

public class TaskWithDateTime extends Task{
    protected LocalDate day;
    protected LocalTime timeOfDay;
    protected String dateTime;

    public TaskWithDateTime(String description, String dateTime) {
        super(description);

        String temp = dateTime;
        this.dateTime = dateTime;

        //extract date, if any
        String regexDate = "\\d{4}[-|/.]\\d{2}[-|/.]\\d{2}";
        Matcher m = Pattern.compile(regexDate).matcher(dateTime);
        if (m.find()) {
            try {
                day = LocalDate.parse(m.group(0).replaceAll("[./|]","-"));
                dateTime = dateTime.replace(m.group(0),
                        day.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
                this.dateTime = dateTime;

                temp = temp.replace(m.group(0), "");
            } catch (DateTimeException e) {
                System.out.println("The date specified is invalid and has automatically been changed to today.");
                this.dateTime = dateTime.replace(m.group(0), LocalDate.now().toString());
                temp = temp.replace(m.group(0), "");
            }

        }

        //extract time, if any
        String regexTime = "\\d{4}";
        m = Pattern.compile(regexTime).matcher(temp);
        if (m.find()) {
            try {
                timeOfDay = LocalTime.parse(m.group(0).substring(0,2) + ":" + m.group(0).substring(2));
                this.dateTime = this.dateTime.replace(m.group(0), timeOfDay.toString());
            } catch (DateTimeException e) {
                System.out.println("The time specified is invalid and has automatically been changed to an hour from now.");
                this.dateTime = this.dateTime.replace(m.group(0),
                        LocalTime.now().plus(1, ChronoUnit.HOURS).toString());
            }
        }
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + dateTime.trim() + ")";
    }
}
