import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;

/**
 * This is a child class of Task, Deadline.
 * Deadline accepts another variable, 'by' that
 * stores the time this Deadline has to be completed
 *
 * @author  Hsiao Jiet
 * @version 1.0
 * @since   2022-1-15
 */

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String n, boolean d, LocalDate b) {
        super(n, d);
        super.type = 'D';
        by = b;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMMM-dd");
        StringBuilder res = new StringBuilder();
        res.append(getTaskIcon()).append(this.getDoneIcon());
        res.append(this.name).append("\n");
        res.append(by.format(formatter)).append("\n");
        return res.toString();
    }

}
