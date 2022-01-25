import java.lang.reflect.Array;
import java.util.ArrayList;

public class Event extends Task {
    String timing;

    public Event (String task, String timing) throws DukeException {
        super(task);
        this.timing = timing;
        this.initials = "E";
        if (task.length() < 1) {
            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + this.timing + ")";
    }

    @Override
    public ArrayList<String> makeCompact() {
        ArrayList<String> out = super.makeCompact();
        out.add(timing);
        return out;
    }
}
