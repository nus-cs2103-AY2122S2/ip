package duke;

public class Todo extends Task{

    public Todo(String taskName){
        super(taskName);
    }

    @Override
    public void printTask(){
        System.out.print("[T]");

        if(this.getDone()){
            System.out.println("[X] " + this.getTaskName());
        } else {
            System.out.println("[ ] " + this.getTaskName());
        }
    }

    @Override
    public String toString(){
        String res = "";
        res += "[T]";
        if(this.getDone()){
            res += "[X]";
        } else {
            res += "[ ]";
        }
        res += " " + this.getTaskName();
        return res;
    }
}
