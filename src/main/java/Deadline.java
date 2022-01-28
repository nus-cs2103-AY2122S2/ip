import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime due;

    public Deadline(String d, LocalDateTime due){
        super(d);
        this.due = due;
        this.type= "D";
    }
    public Deadline(String d, String done, LocalDateTime due){
        super(d,done);
        this.due = due;
        this.type= "D";
    }

    public String getDue() {
        return due.format(OUT_DTF);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + due.format(OUT_DTF) +") ";
    }

}
