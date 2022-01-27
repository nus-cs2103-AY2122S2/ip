package duke.task;

public abstract class Task {
    protected String title;
    protected Boolean isDone;
    protected TaskType type;

    public Task(String title){
        this.title = title;
        this.isDone = false;
    }
    
    public Task(String title, Boolean isDone){
        this.title = title;
        this.isDone = isDone;
    }

    public String getTitle() {
        return this.title;
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public void markAsUndone(){
        this.isDone = false;
    }

    public String getType(){
        return this.type.getInitial();
    }

    public String getStatusIcon(){
        return (isDone ? "X" : " ");
    }

    public String toOutputLine(){
        return this.getType() + " | " + (isDone ? "1 " : "0 ") + "| " + this.title;
    }

    @Override
    public String toString(){
        return this.title;
    }
}