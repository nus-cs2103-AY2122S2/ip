package duke;

import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime due;

    /**
     * Constructor for Deadline
     * @param d for task
     * @param due deadline
     */
    public Deadline(String d, LocalDateTime due){
        super(d);
        this.due = due;
        this.type= "D";
    }

    /**
     * Constructor for deadline
     * @param d for task
     * @param done check for done task
     * @param due deadline
     */
    public Deadline(String d, String done, LocalDateTime due){
        super(d,done);
        this.due = due;
        this.type= "D";
    }

    /**
     * return deadline
     * @return a string which represents the time
     */
    public String getDue() {
        return due.format(OUT_DTF);
    }

    /**
     * Overriding ToString method
     * @return
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + due.format(OUT_DTF) +") ";
    }

}
