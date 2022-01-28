import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class Deadline extends Task {
    String deadline;
    LocalDate time;

    DateTimeFormatterBuilder dateTimeFormatterBuilder = new DateTimeFormatterBuilder()
            .append(DateTimeFormatter.ofPattern(
                    "" +
                            "[yyyy-MM-dd HH:mm:ss]" +
                            "[yyyy-MM-dd]" +
                            "[yyyy/MM/dd]" +
                            "[yyyy-MM-dd HH:mm a]"
            ));

    DateTimeFormatter dateTimeFormatter = dateTimeFormatterBuilder.toFormatter();

    public Deadline(String deadline, String time) {
        super(deadline);
        this.deadline = deadline;

        try {
            this.time = LocalDate.parse(time, dateTimeFormatter);
        } catch (DateTimeException de) {
            System.out.println("sorry, this is not a valid time");
        }
    }

    @Override
    public String toString() {
        String temp = time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + "(by: " + temp + ")";
    }
}
