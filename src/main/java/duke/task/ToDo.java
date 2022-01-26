package duke.task;

public class ToDo extends Task {

    public ToDo(String desc, boolean isComp){
        super(desc, isComp);
    }

    @Override
    public String toString(){
        String temp = "[T] " + super.toString();
        return temp;
    }
}
