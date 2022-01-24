import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {
    protected Date at;

    public Event(String eventName, String at) throws DukeException {
        super(eventName);
        try {
            this.at = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(at);
        } catch (Exception e) {
            throw new DukeException("Please enter the date in \"dd / MM / yyyy HH:mm\" format");
        }
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH:mm");
        return String.format("[E]%s (at: %s)", super.toString(), sdf.format(this.at));
    }

}
