package tasks;

public abstract class Task {
    protected String detail;
    protected boolean marked;

    public void mark(){
        this.marked = true;
    }

    public void unmark(){
        this.marked = false;
    }

    public Task(String detail){
        this.detail = detail;
        this.marked = false;
    }
}
