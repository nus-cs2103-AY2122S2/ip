public class Task implements Message{
    private String taskName;
    private char done;
    public Task(String taskName){
        this.taskName = taskName;
        this.done = ' ';
    }

    public void markDone(){
        this.done = 'X';
    }

    public void markunDone(){
        this.done = ' ';
    }

    public String getTaskName(){
        return this.taskName;
    }

    public String reply(){
        String s = String.format("[%c] %s",this.done,this.taskName);
        return s;
    }
}
