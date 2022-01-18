public class TodoTask extends Task{

    public TodoTask(String title){
        super(title);
        this.type = "T";
    }
    public TodoTask(String title, Boolean isDone){
        super(title, isDone);
        this.type = "T";
    }
}
