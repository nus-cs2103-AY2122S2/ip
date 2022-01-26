package task;

public class Deadline extends Task {
    public String tag;
    public String time;

    public Deadline(String description, String time){
        super(description, "D");
        this.tag = "D";
        this.time = time;
    }

    public String getTag(){
        return "[" + tag + "]";
    }
    public String getTime(){
        return "(by: " + time + ")";
    }
}
