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
    protected String by;

    public Deadline(String n, int d, String b) {
        super(n, d);
        super.type = 'D';
        by = b;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(getTaskIcon()).append(" - ");
        res.append(this.getDoneIcon()).append(" - ");
        res.append(this.name).append(" - ");
        res.append(by).append("\n");
        return res.toString();
    }

}
