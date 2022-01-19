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
    protected String at;

    public Event(String n, boolean d, String a) {
        super(n, d);
        super.type = 'E';
        at = a;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(getTaskIcon()).append(this.getDoneIcon());
        res.append(this.name).append("\n");;
        res.append(at).append("\n");
        return res.toString();
    }
}
