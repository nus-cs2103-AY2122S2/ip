/*
A type of task that accepts a start and end time
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
