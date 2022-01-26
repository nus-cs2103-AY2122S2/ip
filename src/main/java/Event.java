import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

class Event extends Task {

    private Date date;
    private DateFormat formatter = new SimpleDateFormat("dd/MM/yy h:mm a");


    public Event(String description, Date date) {
        super(description);
        this.date = date;
    }

    public Event(String description, boolean hasCompleted, Date date) {
        super(description);
        this.status = hasCompleted;
        this.date = date;

    }

    @Override
    public String toString() {
        return "[" + Type.E + "]";
    }

    @Override
    String getDescription() {
        return this.description + " (at: " + formatter.format(date).toString() + ")";
    }
}