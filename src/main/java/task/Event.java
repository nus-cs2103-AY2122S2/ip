package task;

public class Event extends Task {
    public String tag;
    public String time;

    public Event(String description, String time){
        super(description, "E");
        this.tag = "E";
        this.time = time;
    }

    @Override
    public String getTag(){
        return "[" + tag + "]";
    }

    public String getTime(){
        return "(at: " + time + ")";
    }

}
