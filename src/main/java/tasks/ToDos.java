package tasks;

public class ToDos extends Task{

    public ToDos(String detail){
        super(detail);
    }

    @Override
    public String toString(){
        if(marked){
            return "[T][X] " + detail;
        } else {
            return "[T][ ] " + detail;
        }
    }
}
