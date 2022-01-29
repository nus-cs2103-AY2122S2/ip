package duke.task;

public class ToDos extends Task {
    public ToDos(String s){
        super(s);
    }

    @Override
    public String show(){
        if(super.getDone()){
            return "[T][X] " + super.taskDescription();
        }
        else{
            return "[T][ ] " + super.taskDescription();
        }
    }

    @Override
    public String storeFormat(){
        return "T" + "|" + super.getDone() + "|" + super.taskDescription() + "\n";
    }

}
