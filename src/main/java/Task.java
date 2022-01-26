import java.io.Serializable;

public class Task implements Serializable{
    protected String desc;    
    protected boolean isDone;    

    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String getDesc() {
        return this.desc;
    }

    public boolean isThisDone() {
        return this.isDone;
    }

    public String getStatusIcon() {
        return "[" + (isDone ? "X" : " ") + "] ";
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + this.desc;
    }
}