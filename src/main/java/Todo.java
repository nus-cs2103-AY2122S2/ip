public class Todo extends Task{

    public Todo(String taskName){
        super(taskName);
    }

    @Override public void printTask(){
        System.out.print("[T]");

        if(this.done){
            System.out.println("[X] " + this.taskName);
        } else {
            System.out.println("[ ] " + this.taskName);
        }
    }
}
