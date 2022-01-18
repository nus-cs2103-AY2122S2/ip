package tasks;

public class ToDos extends Task{


    @Override
    public String toString(){
        if(marked){
            return "[T][X] " + detail;
        } else {
            return "[T][ ] " + detail;
        }
    }
}
