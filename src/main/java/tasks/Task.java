package tasks;

public class Task {
    private String detail;
    private boolean marked;

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

    @Override
    public String toString(){
        if(marked){
            return "[X] " + detail;
        } else {
            return "[ ] " + detail;
        }
    }
}
