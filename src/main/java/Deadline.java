import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task implements DateValidator {
    private static final String INDENT = "     ";
    //private static final String LINE = "    ____________________________________________________________";

    LocalDate deadline;

    public Deadline(int rank, String description) throws DateTimeParseException {
        super(rank, description.split(" /by ")[0]);

        try {
            this.deadline = validDate(description.split(" /by ")[1]);
            confirmationMessage(rank, description.split(" /by ")[0], this.deadline);
        } catch (DateTimeParseException e) {
            String exceptionMsg = INDENT+"Please input date in a valid date-time format.";
            throw new DateTimeParseException(exceptionMsg, description.split(" /by ")[1], e.getErrorIndex());
        } catch (ArrayIndexOutOfBoundsException e) {
            String exceptionMsg = INDENT+"Please include a task deadline";
            throw new ArrayIndexOutOfBoundsException(exceptionMsg);
        }
    }

    public static void confirmationMessage(int rank, String description, LocalDate deadline) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");
        System.out.println(INDENT+"Got it. I've added this task:");
        System.out.println(INDENT+"  "+rank+" "+description+" (by: "+formatter.format(deadline)+")");
        System.out.println(INDENT+"Now you have "+rank+" tasks in the list.");
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }

    public LocalDate validDate(String dateStr) throws DateTimeParseException {
        return LocalDate.parse(dateStr);
    }
}