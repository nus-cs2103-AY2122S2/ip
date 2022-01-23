import java.io.FileInputStream;

public class Task {
    protected String description;
    public String saveFormat;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon(){
        return isDone ? "X" : " ";
    }
    public void setDone(){
        isDone = true;
    }
    public void setNotDone(){
        isDone = false;
    }
    public String getDescription(){
        return this.description;
    }

    @Override
    public String toString(){
        return "[" + getStatusIcon() +"] " + description;
    }


}
