package Duke.tasks;
import java.util.Date;

public class Event extends Task{
    Date date;

    public Event(String item, Date date) {
        super(item);
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("E | %s | %s | %s", this.getStatusIcon(), super.toString(),
            this.date);
    }
}
