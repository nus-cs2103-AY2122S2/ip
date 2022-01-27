import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    /*
        if user didn't type "/by" (byIdx == -1)
            throw new Error "you didnt' type /by bro, try again"
        if user didnt' type a desc
            throew new error you didnt type a description man, try again
    */
    public Deadline(String s) throws LilyException {
        super(findDescStart(s));
        by = LocalDate.parse(s.substring(s.indexOf("/by") + 4));
    }

    private static String findDescStart(String s) throws LilyException {
        try {
            return s.substring(9, s.indexOf("/by") - 1);
        } catch (StringIndexOutOfBoundsException e) {
            throw new LilyException(e.getMessage());
        }
    }
    
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("dd MMM yy")) + ")";
    }
} 