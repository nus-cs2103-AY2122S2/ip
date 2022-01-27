package task;

public class Deadline extends Task {
    public String time;

    public Deadline(String description, String time){
        super(description);
        this.time = time;
    }
    public String getTime(){
        return this.time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " " + this.time;
    }
}
