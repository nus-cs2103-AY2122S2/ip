public class Task implements Message{
    private String taskName;
    public Task(String taskName){
        this.taskName=taskName;
    }

    public String reply(){
        return this.taskName;
    }
}
