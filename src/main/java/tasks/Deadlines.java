package tasks;

public class Deadlines extends Task{
    private String deadline;
    public Deadlines(String detail, String deadline){
        super(detail);
        this.deadline = deadline;
    }

    @Override
    public String toString(){
        if(marked){
            return "[D][X] " + detail + "(by:" + deadline +")";
        } else {
            return "[D][ ] " + detail + "(by:" + deadline +")";
        }
    }
}
