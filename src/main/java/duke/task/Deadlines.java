package duke.task;

import java.time.LocalDate;

public class Deadlines extends Task{

    public LocalDate date;

    /**
     * Creates a Deadlines instance
     * @param s Task description
     * @param time Date which task is due
     */
    public Deadlines(String s, String time) {
        super(s);
        this.date = LocalDate.parse(time);
        super.date = this.date;
    }

    /**
     * Returns a String representation of the task
     * @return String representation of this Deadlines
     */
    @Override
    public String show(){
        if(super.done){
            return "[D][X] " + s + " (by: " + date + ")";
        }
        else{
            return "[D][ ] " + s + " (by: " + date + ")";
        }
    }

    /**
     * Returns a String format of the task to store
     * @return String format of this Deadlines
     */
    @Override
    public String storeFormat(){
        return "D|" + super.done + "|" + super.s + "|" + this.date + "\n";
    }
}
