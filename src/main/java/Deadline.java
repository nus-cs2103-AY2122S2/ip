public class Deadline extends Task {
    protected String due;

    public Deadline(String d, String due){
        super(d);
        this.due = due;
        this.type= "D";
    }
    public Deadline(String d, String done, String due){
        super(d,done);
        this.due = due;
        this.type= "D";
    }

    public String getDue() {
        return this.due;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + due +") ";
    }

}
