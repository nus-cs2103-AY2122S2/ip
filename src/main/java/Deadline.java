/*
A type of task that accepts an endTime
*/
public class Deadline extends Task {
    protected String by;

    public Deadline(String n, boolean d, String b) {
        super(n, d);
        super.type = 'D';
        by = b;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(getTaskIcon()).append(this.getDoneIcon());
        res.append(this.name).append("\n");
        res.append(by).append("\n");
        return res.toString();
    }

}
