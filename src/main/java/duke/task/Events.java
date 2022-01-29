package duke.task;

import java.time.LocalDate;

public class Events extends Task {

    public LocalDate date;

    /**
     * Creates an Events instance
     * @param s Task description
     * @param time Date which task is due
     */
    public Events (String s, String time){
        super(s);
        this.date = LocalDate.parse(time);
        super.date = this.date;
    }

    /**
     * Returns a String representation of the task
     * @return String representation of this Events
     */
    @Override
    public String show(){
        if(super.done){
            return "[E][X] " + s + " (at: " + date + ")";
        }
        else{
            return "[E][ ] " + s + " (at: " + date + ")";
        }
    }

    /**
     * Returns a String format of the task to store
     * @return String format of this Events
     */
    @Override
    public String storeFormat(){
        return "E|" + super.done + "|" + super.s + "|" + this.date + "\n";
    }

}
