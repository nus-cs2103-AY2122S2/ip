import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This is a child class of Task, Event.
 * Event accepts another variable, 'at' that
 * stores the time this Event is taking place
 *
 * @author  Hsiao Jiet
 * @version 1.0
 * @since   2022-1-15
 */

public class Event extends Task {
    protected LocalDate at;

    public Event(String n, boolean d, LocalDate a) {
        super(n, d);
        super.type = 'E';
        at = a;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMMM-dd");
        StringBuilder res = new StringBuilder();
        res.append(getTaskIcon()).append(this.getDoneIcon());
        res.append(this.name).append("\n");;
        res.append(at.format(formatter)).append("\n");
        return res.toString();
    }
}
