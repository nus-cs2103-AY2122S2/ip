public class Deadline extends Task {
    protected String due;

    public Deadline(String d, String due){
        super(d);
        this.due = due;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + due +") ";
    }

}
