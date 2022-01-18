package tasks;

public class Events extends Task{
    private String date;
    public Events(String detail, String date){
        super(detail);
        this.date = date;
    }

    @Override
    public String toString(){
        if(marked){
            return "[E][X] " + detail + "(at:" + date +")";
        } else {
            return "[E][ ] " + detail + "(at:" + date +")";
        }
    }
}
