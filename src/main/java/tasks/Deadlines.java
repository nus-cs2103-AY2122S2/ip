package tasks;

public class Deadlines extends Task{

    @Override
    public String toString(){
        if(marked){
            return "[D][X] " + detail;
        } else {
            return "[D][ ] " + detail;
        }
    }
}
