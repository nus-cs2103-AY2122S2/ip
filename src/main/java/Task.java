public class Task {
    protected String title;
    protected Boolean isDone;

    public Task(String title){
        this.title = title;
        this.isDone = false;
    }
    
    public Task(String title, Boolean isDone){
        this.title = title;
        this.isDone = isDone;
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public void markAsUndone(){
        this.isDone = false;
    }

    public String getTitle(){
        return this.title;
    }

    public String getStatusIcon(){
        return (isDone ? "X" : " ");
    }
}