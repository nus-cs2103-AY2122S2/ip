package tasks;

public class Events extends Task{

    @Override
    public String toString(){
        if(marked){
            return "[E][X] " + detail;
        } else {
            return "[E][ ] " + detail;
        }
    }
}
